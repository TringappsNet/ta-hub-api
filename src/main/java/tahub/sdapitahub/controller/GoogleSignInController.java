package tahub.sdapitahub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import tahub.sdapitahub.dto.GoogleSignInResponseDto;
import tahub.sdapitahub.config.GoogleSignInConfig;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.TaUserRepository;
import tahub.sdapitahub.service.GoogleSignInService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Tag(name = "Google Sign-in", description = "Operations related to google")
@RequestMapping("/api")
public class GoogleSignInController {

    private static final Logger logger = Logger.getLogger(GoogleSignInController.class.getName());

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
    public ResponseEntity<Object> signIn(HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        String accessToken = (String) request.getSession().getAttribute("accessToken");
        Map<String, Object> responseBody = new HashMap<>();
        if (accessToken == null || !googleSignInService.isValidAccessToken(accessToken)) {
            AuthorizationCodeRequestUrl authorizationUrl = authorizationCodeFlow.newAuthorizationUrl()
                    .setRedirectUri(googleSignInConfig.getRedirectUri())
                    .setScopes(GoogleSignInConfig.SCOPES);
            String generatedUrl = authorizationUrl.build();
            logger.info("Generated Google Sign-in URL: " + generatedUrl);

            responseBody.put("redirectUrl", generatedUrl);
        } else {
            Optional<TaUser> existingUser = taUserRepository.findByAccessToken(accessToken);
            if (existingUser.isPresent()) {
                request.getSession().setMaxInactiveInterval(24 * 60 * 60);
                request.getSession().setAttribute("user", existingUser);

//                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("sessionId", request.getSession().getId());
                responseBody.put("user", existingUser.get());
                responseBody.put("isNewUser", false);


            } else {
                responseBody.put("error", "Invalid access token.");
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid access token.");
            }
        }
        return ResponseEntity.ok(responseBody);
    }


    @GetMapping("/google-sign-in/callback")
    public ResponseEntity<Object> callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        try {
            GoogleSignInResponseDto responseDto = googleSignInService.handleCallback(code);
            // Store tokens in session
            request.getSession().setAttribute("accessToken", responseDto.getAccessToken());
            request.getSession().setAttribute("refreshToken", responseDto.getRefreshToken());
            // Set session timeout to 24 hours
            request.getSession().setMaxInactiveInterval(24 * 60 * 60);
            request.getSession().setAttribute("user", responseDto);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("sessionId", request.getSession().getId());
            responseBody.put("user", responseDto);
            responseBody.put("isNewUser", true);

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during Google Sign-in callback", e);
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("An error occurred during Google Sign-in");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session
        request.getSession().invalidate();

        // Remove the session cookie
        Cookie cookie = new Cookie("sessionId", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Successfully logged out");

        return ResponseEntity.ok(responseBody);
    }
}