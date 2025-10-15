package com.example.oath2authenticator.TiuOath2Activity.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String displayName;
    private String avatarUrl;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters

    public void setId(long id){
        this.id=id;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setDisplayName(String displayName){
        this.displayName=displayName;
    }
    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl=avatarUrl;
    }
    public void setBio(String bio){
        this.bio=bio;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public String getBio() {
        return bio;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}