// package com.example.oath2authenticator.TiuOath2Activity.controllers;

// import com.example.oath2authenticator.TiuOath2Activity.entities.User;
//import com.example.oath2authenticator.TiuOath2Activity.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
//import java.util.List;

//@Controller
//@RestController
//@RequestMapping("/api/users")

//public class UserController {

//@Autowired
//private UserRepository userRepository;

// @GetMapping("/home")
// public String home() {
//     return "Home";
//   }

//  @GetMapping("/Profile")
//   public String profile(Model model, OAuth2AuthenticationToken auth) {
//       String email = auth.getPrincipal().getAttribute("email");
//       User user = userRepository.findByEmail(email).orElseThrow();
//       model.addAttribute("user", user);
//        return "profile";
//   }

//   @PostMapping("/Profile")
//   public String updateProfile(@ModelAttribute User updatedUser, OAuth2AuthenticationToken auth) {
//       String email = auth.getPrincipal().getAttribute("email");
//       User user = userRepository.findByEmail(email).orElseThrow();

//       user.setDisplayName(updatedUser.getDisplayName());
//     user.setBio(updatedUser.getBio());
//      user.setUpdatedAt(LocalDateTime.now());

//      userRepository.save(user);
//       return "redirect:/Profile";
//   }

//       @GetMapping
//      public List<User> getAllUsers() {
            //         return userRepository.findAll();
//     }

//     @PostMapping
//     public User createUser(@RequestBody User user) {
//         user.setCreatedAt(LocalDateTime.now());
        //         user.setUpdatedAt(LocalDateTime.now());
//          return userRepository.save(user);
//      }

//}