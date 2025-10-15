package com.example.oath2authenticator.TiuOath2Activity.security;

import com.example.oath2authenticator.TiuOath2Activity.entities.AuthProvider;
import com.example.oath2authenticator.TiuOath2Activity.entities.User;
import com.example.oath2authenticator.TiuOath2Activity.repositories.AuthProviderRepository;
import com.example.oath2authenticator.TiuOath2Activity.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthProviderRepository authProviderRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = token.getPrincipal();

        String provider = token.getAuthorizedClientRegistrationId().toUpperCase();

        Object subAttr = oauthUser.getAttribute("sub");
        Object idAttr = oauthUser.getAttribute("id");
        if (subAttr == null && idAttr == null) {
            logger.warn("OAuth2 provider did not return id/sub attribute for provider={}", provider);
        }
        String providerUserId = (subAttr != null ? subAttr : idAttr) != null ? (subAttr != null ? subAttr : idAttr).toString() : null;

        Object emailObj = oauthUser.getAttribute("email");
        String email = emailObj != null ? emailObj.toString() : null;

        Object nameObj = oauthUser.getAttribute("name");
        String name = nameObj != null ? nameObj.toString() : null;

        Object avatarObj1 = oauthUser.getAttribute("picture");
        Object avatarObj2 = oauthUser.getAttribute("avatar_url");
        String avatar = avatarObj1 != null ? avatarObj1.toString()
                : (avatarObj2 != null ? avatarObj2.toString() : null);

        // If email is null for providers like GitHub, you might need to call /user/emails endpoint.
        if (email == null) {
            logger.warn("Email is null for oauth user; provider={}, providerUserId={}", provider, providerUserId);
        }

        // find or create auth provider + user
        AuthProvider authProvider = null;
        if (providerUserId != null) {
            authProvider = authProviderRepository
                    .findByProviderAndProviderUserId(provider, providerUserId)
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setDisplayName(name);
                        newUser.setAvatarUrl(avatar);
                        newUser.setCreatedAt(LocalDateTime.now());
                        newUser.setUpdatedAt(LocalDateTime.now());
                        userRepository.save(newUser);

                        AuthProvider ap = new AuthProvider();
                        ap.setProvider(provider);
                        ap.setProviderUserId(providerUserId);
                        ap.setProviderEmail(email);
                        ap.setUser(newUser);
                        return authProviderRepository.save(ap);
                    });
        }

        // redirect to /profile (this mapping must exist in a Controller as a view)
        response.sendRedirect("/profile");
    }
}
