package com.shamim.eticket.security;

import com.shamim.eticket.global.constants.AppUrl;
import com.shamim.eticket.handlers.CustomLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AppUrl.LOGIN, AppUrl.REGISTER, "/css/**", "/js/**").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .requestMatchers("/operator/**").hasRole("BUS_OPERATOR")

                        .requestMatchers("/user/**").hasRole("USER")

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage(AppUrl.LOGIN)
                        .loginProcessingUrl(AppUrl.LOGIN)
                        .successHandler(authenticationSuccessHandler())
                        .failureUrl(AppUrl.LOGIN_FAILURE)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }
}