package com.habittracker.auth.entity;

import com.habittracker.common.entity.BaseAuditedEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseAuditedEntity {

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false, length = 50)
    private AuthProvider authProvider;

    @Column(name = "provider_id", length = 255)
    private String providerId; // Google 'sub' claim for OAuth2

    @Column(name = "timezone", nullable = false, length = 50)
    @Builder.Default
    private String timezone = "Asia/Kolkata";

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean active = true;

    public enum AuthProvider {
        GOOGLE,
        EMAIL_OTP
    }
}