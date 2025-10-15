package com.example.oath2authenticator.TiuOath2Activity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController {

    @RequestMapping("/app-error")
    public String handleAppError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        String message = "An unexpected error occurred.";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == 404) {
                message = "Page not found.";
            } else if (statusCode == 403) {
                message = "Access denied.";
            } else if (statusCode == 500) {
                message = "Internal server error.";
            }
        }

        model.addAttribute("message", message);
        return "error";
    }
}
