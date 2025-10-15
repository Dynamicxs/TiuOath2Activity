package com.example.oath2authenticator.TiuOath2Activity.config;

import com.example.oath2authenticator.TiuOath2Activity.security.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private OAuth2LoginSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/**","/logout")
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Needed for H2 console
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")  // Custom login page
                        .successHandler(successHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")        // URL to trigger logout
                        .logoutSuccessUrl("/")       // Redirect after logout
                        .permitAll()                 // Allow anyone to access it
                );

        return http.build();
    }

}