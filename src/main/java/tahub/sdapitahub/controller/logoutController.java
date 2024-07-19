package tahub.sdapitahub.controller;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;




import java.util.HashMap;
import java.util.Map;

import java.util.logging.Logger;

@RestController

@RequestMapping("/api")
public class logoutController {

    private static final Logger logger = Logger.getLogger(logoutController.class.getName());

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