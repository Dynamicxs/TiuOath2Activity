package com.example.oath2authenticator.TiuOath2Activity.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("http://localhost:3000")
    public String login() {
        return "login"; // use src/main/resources/templates/login.html
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        return "redirect:/Logout";
    }
}