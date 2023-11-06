package com.manahotel.be.config;

import com.manahotel.be.security.JwtAuthenticationFilter;
import com.manahotel.be.service.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Autowired
    private final LogoutService logoutService;

    private static final String[] WHITE_LIST_URL = {"/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers(WHITE_LIST_URL)
                .permitAll()
                .requestMatchers("/customer/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/Floor/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/goods/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/goods-unit/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/inventory-check/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/room-class/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/room/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/price-list/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/reservation/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/reservation-detail/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/order/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/reservation-detail-customer/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout().logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutService)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                );
        return http.build();
    }

}
