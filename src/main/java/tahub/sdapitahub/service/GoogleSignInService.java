package tahub.sdapitahub.service;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.config.GoogleSignInConfig;
import tahub.sdapitahub.dto.google.GoogleSignInResponseDto;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GoogleSignInService {

    @Autowired
    private AuthorizationCodeFlow authorizationCodeFlow;

    @Autowired
    private GoogleSignInConfig googleSignInConfig;

    @Autowired
    private TaUserRepository taUserRepository;

    private static final Logger logger = Logger.getLogger(tahub.sdapitahub.service.GoogleSignInService.class.getName());

    private static final String GENERIC_URL = "https://www.googleapis.com/oauth2/v3/userinfo";


    public GoogleSignInResponseDto handleCallback(String code) throws IOException, GeneralSecurityException {
        TokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(code)
                .setRedirectUri(googleSignInConfig.getRedirectUri())
                .execute();
        String accessToken = tokenResponse.getAccessToken();
        String refreshToken = tokenResponse.getRefreshToken();
        Long expiresIn = tokenResponse.getExpiresInSeconds();
        String idTokenString = (String) tokenResponse.get("id_token");
        String tokenType = tokenResponse.getTokenType();

        // Verify and decode the ID token
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(googleSignInConfig.getClientId()))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new GeneralSecurityException("Invalid ID token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();

        // Use access token to get additional user information (e.g., username)
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        GenericUrl url = new GenericUrl(GENERIC_URL);
        HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setAuthorization("Bearer " + accessToken);
        HttpResponse response = request.execute();

        JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(response.getContent());
        Map<String, Object> userInfo = jsonParser.parse(Map.class);
        String username = (String) userInfo.get("name");

        GoogleSignInResponseDto responseDto = new GoogleSignInResponseDto();
        responseDto.setAccessToken(accessToken);
        responseDto.setRefreshToken(refreshToken);
        responseDto.setExpiresIn(expiresIn);
        responseDto.setIdToken(idTokenString);
        responseDto.setTokenType(tokenType);
        responseDto.setEmail(email);
        responseDto.setUsername(username);
        googleSignInConfig.saveUserDetails(responseDto);

        return responseDto;
    }

    public boolean isValidAccessToken(String accessToken) {
        if (accessToken == null || accessToken.trim().isEmpty()) {
            logger.warning("Access token is null or empty");
            return false;
        }

        try {
            // Fetch the user details from the database using the access token
            Optional<TaUser> taUserOptional = taUserRepository.findByAccessToken(accessToken);
            if (taUserOptional.isEmpty()) {
                logger.warning("No user found with the given access token");
                return false;
            }

            TaUser taUser = taUserOptional.get();

            LocalDateTime accessTokenCreatedAt = taUser.getgAccessTokenCreatedAt();
            long expiresIn = taUser.getgTokenExpiresIn();
            OffsetDateTime offsetDateTime = accessTokenCreatedAt.atOffset(ZoneOffset.UTC);
            Instant accessTokenExpiresAt = offsetDateTime.toInstant().plus(expiresIn, ChronoUnit.SECONDS);

            // Check if the access token is expired
            Instant now = Instant.now();
            if (now.isAfter(accessTokenExpiresAt)) {
                logger.warning("Access token is expired");
                return false;
            }

            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking access token validity", e);
            return false;
        }
    }

}
