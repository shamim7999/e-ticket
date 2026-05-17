package com.shamim.eticket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            // If not authenticated or explicitly an anonymous context filter token
            if (auth == null ||
                    !auth.isAuthenticated() ||
                    auth instanceof AnonymousAuthenticationToken) {

                // Return a fallback flag so you can catch it in your service or a @PrePersist hook
                return Optional.of("SELF_REGISTRATION");
            }

            return Optional.of(auth.getName());
        };
    }
}