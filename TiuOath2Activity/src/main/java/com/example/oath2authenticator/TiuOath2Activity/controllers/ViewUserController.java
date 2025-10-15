package com.example.oath2authenticator.TiuOath2Activity.controllers;

import com.example.oath2authenticator.TiuOath2Activity.entities.User;
import com.example.oath2authenticator.TiuOath2Activity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ViewUserController {

    @Autowired
    private UserRepository userRepository;

    // Home page (template: src/main/resources/templates/home.html)
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // Profile view — mapped to /profile (note: lowercase)
    @GetMapping("/profile")
    public String profile(Model model, OAuth2AuthenticationToken auth) {
        if (auth == null) {
            return "redirect:/login";
        }

        String email = auth.getPrincipal().getAttribute("email");
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            // If user not yet created, show a simple page or redirect
            return "profile"; // still render profile view (empty) or handle creation elsewhere
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User updatedUser, OAuth2AuthenticationToken auth) {
        if (auth == null) {
            return "redirect:/login";
        }
        String email = auth.getPrincipal().getAttribute("email");
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setDisplayName(updatedUser.getDisplayName());
        user.setBio(updatedUser.getBio());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return "redirect:/profile";
    }
}
