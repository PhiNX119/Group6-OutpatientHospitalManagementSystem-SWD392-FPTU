package com.swd392.group6.outpatientmanagementsystem.config;

import com.swd392.group6.outpatientmanagementsystem.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public SpringSecurity(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(
                        csrf -> csrf.disable()
                ).authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("login", "/error-403", "/error-404").permitAll()
                                .requestMatchers("/js/**", "/css/**", "/images/**").permitAll()
                                .requestMatchers("/account/**").hasRole("ADMIN")
                                .requestMatchers("/medicine/**").hasAnyRole("ADMIN", "PHARMACY-STAFF")
                                .requestMatchers("/patientInfo/**").hasAnyRole("ADMIN", "RECEPTION_COUNTER_STAFF")
                                .anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home", true)
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                //.deleteCookies("JSESSIONID")
                                .logoutSuccessHandler(logoutSuccessHandler())
                ).exceptionHandling(
                        httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                                .accessDeniedPage("/error-403")
                                .defaultAuthenticationEntryPointFor(
                                        (request, response, authException) -> response.sendRedirect("/error-404"),
                                        new AntPathRequestMatcher("/**")
                                )
                );
        return http.build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}