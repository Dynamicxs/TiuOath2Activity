package com.example.oath2authenticator.TiuOath2Activity.entities;

import jakarta.persistence.*;

@Entity
public class AuthProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider; // GITHUB, GOOGLE, etc.
    private String providerUserId;
    private String providerEmail;

    @ManyToOne
    private User user;

    public AuthProvider() {
    }

    // useful constructor — set fields
    public AuthProvider(String provider, String providerUserId, String providerEmail, User user) {
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.providerEmail = providerEmail;
        this.user = user;
    }

    // getters & setters ...
    // (keep the getters and setters you already have; ensure they reference the 'user' field too)
    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public void setProviderEmail(String email) {
        this.providerEmail = email;
    }
    public String getProvider() {
        return provider;
    }
    public String getProviderUserId() {
        return providerUserId;
    }

}
