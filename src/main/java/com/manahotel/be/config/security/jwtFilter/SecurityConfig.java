package com.manahotel.be.config.security.jwtFilter;

import com.manahotel.be.config.security.JwtAuthenticationFilter;
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
                .requestMatchers("/customer/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/staff/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/account/**").hasAnyAuthority("ROLE_MANAGER","ROLE_RECEPTIONIST")
                .requestMatchers("/Floor/list-by-floor").hasAnyAuthority("ROLE_MANAGER","ROLE_RECEPTIONIST")
                .requestMatchers("/room-class/list-by-room-class").hasAnyAuthority("ROLE_MANAGER","ROLE_RECEPTIONIST")
                .requestMatchers("/Floor/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/goods/category/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/goods-unit").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/goods/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/goods-unit/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/inventory-check/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/room-class").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/room-class/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/room/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/price-list/{id}").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/price-list").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/price-list/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/reservation/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/reservation-detail/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/order/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/reservation-detail-customer/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/policy/time_use/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/policy/{policyName}").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/policy/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/fund-book/create_fund_book_by_deposit").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/fund-book/by_staff_name").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/fund-book/{id}").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
                .requestMatchers("/fund-book/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/import-goods/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/qr-code/**").hasAnyAuthority("ROLE_RECEPTIONIST", "ROLE_MANAGER")
                .requestMatchers("/overview/**").hasAnyAuthority("ROLE_MANAGER")
                .requestMatchers("/invoice/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_RECEPTIONIST")
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
