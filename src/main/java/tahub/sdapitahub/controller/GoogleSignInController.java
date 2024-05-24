package tahub.sdapitahub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tahub.sdapitahub.dto.GoogleSignInResponseDto;
import tahub.sdapitahub.config.GoogleSignInConfig;
import tahub.sdapitahub.repository.TaUserRepository;
import tahub.sdapitahub.service.GoogleSignInService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

    @RestController
    @Tag(name = "Google Sign-in", description = "Operations related to google")
    @RequestMapping("/api")
    public class GoogleSignInController {

        private static final Logger logger = Logger.getLogger(tahub.sdapitahub.controller.GoogleSignInController.class.getName());
        private static final String REDIRECT_URL = "http://localhost:5173/navbar";

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
        public ResponseEntity<String> signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String accessToken = (String) request.getSession().getAttribute("accessToken");
            if (accessToken == null || !googleSignInService.isValidAccessToken(accessToken)) {
                AuthorizationCodeRequestUrl authorizationUrl = authorizationCodeFlow.newAuthorizationUrl()
                        .setRedirectUri(googleSignInConfig.getRedirectUri())
                        .setScopes(GoogleSignInConfig.SCOPES);
                String generatedUrl = authorizationUrl.build();
                logger.info("Generated Google Sign-in URL: " + generatedUrl);
                return ResponseEntity.ok(generatedUrl);
            } else {
                response.sendRedirect(REDIRECT_URL);
                return ResponseEntity.ok().build();
            }
        }

        @GetMapping("/google-sign-in/callback")
        public void callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
            try {
                GoogleSignInResponseDto responseDto = googleSignInService.handleCallback(code);
                // Store tokens in session
                request.getSession().setAttribute("accessToken", responseDto.getAccessToken());
                request.getSession().setAttribute("refreshToken", responseDto.getRefreshToken());
                response.sendRedirect(REDIRECT_URL);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during Google Sign-in callback", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred during Google Sign-in");
            }
        }
    }
