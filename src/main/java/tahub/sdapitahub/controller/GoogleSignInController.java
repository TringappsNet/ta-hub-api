        package tahub.sdapitahub.controller;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
        import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
        import com.google.api.client.auth.oauth2.Credential;
        import com.google.api.client.auth.oauth2.TokenResponse;
        import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
        import jakarta.servlet.http.HttpServletResponse;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;
        import tahub.sdapitahub.dto.GoogleSignInResponseDto;
        import tahub.sdapitahub.config.GoogleSignInConfig;

        import java.io.IOException;
        import java.security.GeneralSecurityException;
        import java.util.Arrays;
        import java.util.List;

        @RestController
        public class GoogleSignInController {

            @Autowired
            private AuthorizationCodeFlow authorizationCodeFlow;

            @Autowired
            private LocalServerReceiver localServerReceiver;

            @Autowired
            private ObjectMapper objectMapper;

            @Autowired
            private GoogleSignInConfig googleSignInConfig;

            private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.email");

            @GetMapping("/google-sign-in")
            public void signIn(HttpServletResponse response) throws IOException {
                AuthorizationCodeRequestUrl authorizationUrl = authorizationCodeFlow.newAuthorizationUrl()
                        .setRedirectUri("http://localhost:8080/google-sign-in/callback")
                        .setScopes(SCOPES);
                response.sendRedirect(authorizationUrl.build());
            }

            @GetMapping("/google-sign-in/callback")
            public ResponseEntity<String> callback(@RequestParam("code") String code) throws IOException, GeneralSecurityException {
                TokenResponse tokenResponse = authorizationCodeFlow.newTokenRequest(code)
                        .setRedirectUri("http://localhost:8080/google-sign-in/callback")
                        .execute();
                String accessToken = tokenResponse.getAccessToken();
                String refreshToken = tokenResponse.getRefreshToken();
                GoogleSignInResponseDto responseDto = new GoogleSignInResponseDto();
                responseDto.setAccessToken(accessToken);
                responseDto.setRefreshToken(refreshToken);
                googleSignInConfig.saveUserDetails(responseDto);

                return ResponseEntity.ok("User signed in successfully." );
            }

        }
