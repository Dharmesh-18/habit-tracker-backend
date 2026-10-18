package com.habittracker.auth.controller;

import com.habittracker.auth.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TestSecurityController {

    // Public endpoint (No token needed)
    @GetMapping("/auth/health")
    public ResponseEntity<Map<String, String>> publicHealth() {
        return ResponseEntity.ok(Map.of("status", "UP", "message", "Auth service is reachable"));
    }

    // Protected endpoint (Requires valid JWT)
    @GetMapping("/test/me")
    public ResponseEntity<Map<String, Object>> getProfile(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(Map.of(
                "userId", currentUser.getId(),
                "email", currentUser.getEmail(),
                "authorities", currentUser.getAuthorities()
        ));
    }
}