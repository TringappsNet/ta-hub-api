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
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.config.GoogleSignInConfig;
import tahub.sdapitahub.dto.GoogleSignInResponseDto;
import tahub.sdapitahub.repository.TaUserRepository;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

@Service
public class GoogleSignInService {

    @Autowired
    private AuthorizationCodeFlow authorizationCodeFlow;

    @Autowired
    private GoogleSignInConfig googleSignInConfig;

    @Autowired
    private TaUserRepository taUserRepository;

    public GoogleSignInResponseDto handleCallback(String code) throws IOException, GeneralSecurityException {
        TokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(code)
                .setRedirectUri(googleSignInConfig.getRedirectUri())
                .execute();
        String accessToken = tokenResponse.getAccessToken();
        String refreshToken = tokenResponse.getRefreshToken();

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        GenericUrl url = new GenericUrl("https://www.googleapis.com/oauth2/v3/userinfo");
        HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setAuthorization("Bearer " + accessToken);
        HttpResponse response = request.execute();

        JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(response.getContent());
        Map<String, Object> userInfo = jsonParser.parse(Map.class);
        String email = (String) userInfo.get("email");
        String username = (String) userInfo.get("name");

        GoogleSignInResponseDto responseDto = new GoogleSignInResponseDto();
        responseDto.setAccessToken(accessToken);
        responseDto.setRefreshToken(refreshToken);
        responseDto.setEmail(email);
        responseDto.setUsername(username);
        googleSignInConfig.saveUserDetails(responseDto);

        return responseDto;
    }

    public boolean isValidAccessToken(String accessToken) {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            String CLIENT_ID = googleSignInConfig.getClientId();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            return idToken != null;
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
