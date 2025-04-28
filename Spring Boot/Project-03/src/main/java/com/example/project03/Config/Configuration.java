package com.example.project03.Config;

import com.example.project03.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Configuration {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/get",
                        "/api/v1/auth/delete/**",
                        "/api/v1/employee/admin/all",
                        "/api/v1/employee/admin/delete/**",
                        "/api/v1/customer/admin/all",
                        "/api/v1/customer/admin/delete/**",
                        "/api/v1/account/admin/activate/**",
                        "/api/v1/account/admin/block/**"
                ).hasAuthority("ADMIN") // Admin
                .requestMatchers(
                        "/api/v1/employee/profile",
                        "/api/v1/employee/update"
                ).hasAuthority("EMPLOYEE") // Employee
                .requestMatchers(
                        "/api/v1/customer/profile",
                        "/api/v1/customer/update",
                        "/api/v1/account/create",
                        "/api/v1/account/details/**",
                        "/api/v1/account/my-accounts",
                        "/api/v1/account/deposit/**",
                        "/api/v1/account/withdraw/**",
                        "/api/v1/account/transfer/**"
                ).hasAuthority("CUSTOMER") // Customer
                .requestMatchers(
                        "/api/v1/auth/register",
                        "/api/v1/employee/register",
                        "/api/v1/customer/register"
                ).permitAll() // Permit-ALL
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}
