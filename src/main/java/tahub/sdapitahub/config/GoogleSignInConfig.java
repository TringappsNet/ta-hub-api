package tahub.sdapitahub.config;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tahub.sdapitahub.dto.GoogleSignInResponseDto;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class GoogleSignInConfig {

    private static final String APPLICATION_NAME = "Ta_Hub";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.email");
    private static final String CLIENT_SECRET_FILE = "/credentials.json";
    private static final String DATA_STORE_DIR = "datastore/";
    private GoogleClientSecrets clientSecrets;

    @Autowired
    private TaUserRepository taUserRepository;

    @Autowired
    public GoogleSignInConfig() throws IOException {
        this.clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(getClass().getResourceAsStream(CLIENT_SECRET_FILE)));
    }

    public void saveUserDetails(GoogleSignInResponseDto responseDto) {
        TaUser existingUser = taUserRepository.findByEmail(responseDto.getEmail());
        if (existingUser != null) {
            existingUser.setgAccessToken(responseDto.getAccessToken());
            existingUser.setgAccessTokenCreatedAt(LocalDateTime.now());
            existingUser.setgTokenExpiresIn(responseDto.getExpiresIn());
            existingUser.setIsActive(true);
            existingUser.setLastUpdated(LocalDateTime.now());
            taUserRepository.update(existingUser);
        } else {
            TaUser.Builder builder = new TaUser.Builder();
            TaUser user = builder
                    .email(responseDto.getEmail())
                    .username(responseDto.getUsername())
                    .roleId(1L)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .lastUpdated(LocalDateTime.now())
                    .build();

            // Set access token, token expiry
            user.setgAccessToken(responseDto.getAccessToken());
            user.setgAccessTokenCreatedAt(LocalDateTime.now());
            user.setgTokenExpiresIn(responseDto.getExpiresIn());
            user.setLastUpdated(LocalDateTime.now());
            user.setIsActive(true);
            taUserRepository.save(user);
        }
    }



    @Bean
    public AuthorizationCodeFlow authorizationCodeFlow() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        File dataStoreDir = new File(DATA_STORE_DIR);
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(dataStoreDir);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(getClass().getResourceAsStream(CLIENT_SECRET_FILE)));

        Builder flowBuilder = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES);
        flowBuilder.setDataStoreFactory(dataStoreFactory);

        return flowBuilder.build();
    }

    public String getClientId() {
        return clientSecrets.getDetails().getClientId();
    }


    public String getRedirectUri() {
        return clientSecrets.getDetails().getRedirectUris().get(0);
    }
}


