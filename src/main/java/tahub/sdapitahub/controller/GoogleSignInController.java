        package tahub.sdapitahub.controller;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
        import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;
        import tahub.sdapitahub.dto.GoogleSignInResponseDto;
        import tahub.sdapitahub.config.GoogleSignInConfig;
        import java.security.Principal;
        import tahub.sdapitahub.repository.TaUserRepository;
        import tahub.sdapitahub.service.GoogleSignInService;

        import java.io.IOException;
        import java.security.GeneralSecurityException;

        @RestController
        public class GoogleSignInController {

            @Autowired
            private GoogleSignInService googleSignInService;

            @Autowired
            private AuthorizationCodeFlow authorizationCodeFlow;

            @Autowired
            private ObjectMapper objectMapper;

            @Autowired
            private GoogleSignInConfig googleSignInConfig;

            @Autowired
            private TaUserRepository taUserRepository;


            @GetMapping("/google-sign-in")
            public void signIn(HttpServletResponse response, HttpServletRequest request) throws IOException {
                String accessToken = (String) request.getSession().getAttribute("accessToken");
                if (accessToken == null || !googleSignInService.isValidAccessToken(accessToken)) {
                    AuthorizationCodeRequestUrl authorizationUrl = authorizationCodeFlow.newAuthorizationUrl()
                            .setRedirectUri(googleSignInConfig.getRedirectUri())
                            .setScopes(GoogleSignInConfig.SCOPES);
                    response.sendRedirect(authorizationUrl.build());
                } else {
                    response.sendRedirect("/welcome");
                }
            }

            @GetMapping("/google-sign-in/callback")
            public ResponseEntity<String> callback(@RequestParam("code") String code, HttpServletRequest request) throws IOException, GeneralSecurityException {
                GoogleSignInResponseDto responseDto = googleSignInService.handleCallback(code);
                // Store tokens in session
                request.getSession().setAttribute("accessToken", responseDto.getAccessToken());
                request.getSession().setAttribute("refreshToken", responseDto.getRefreshToken());
                return ResponseEntity.ok("User signed in successfully.");
            }
        }