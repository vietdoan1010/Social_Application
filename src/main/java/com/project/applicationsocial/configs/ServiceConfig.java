package com.project.applicationsocial.configs;

import com.project.applicationsocial.fillter.JwtAuthFilter;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ServiceConfig{

    @Autowired
    private JwtAuthFilter authFilter;


    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public HttpBasicConfigurer<HttpSecurity> configSwagger(HttpSecurity http) throws Exception {
        return  http.authorizeRequests()
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/signin","/auth/getAllUser","/auth/addNewUser", "/auth/generateToken","/swagger-ui/**", "/images/**",
                       "auth/update/{id}",
                        "/v3/api-docs/**",
                        "/api-docs/**",
                        "/v2/api-docs/**",
                        "/home/static/**",
                        "/actuator/**",
                        "/static/**",
                        "/login/**",
                        "/swagger-resources/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/user/*").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/admin/*").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}