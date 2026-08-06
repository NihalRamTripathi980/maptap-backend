//package com.nihalramtripathi.commonsecurity.config;
//
//import com.nihalramtripathi.commonsecurity.exception.JwtAuthenticationEntryPoint;
//import com.nihalramtripathi.commonsecurity.filter.JwtAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtFilter;
//    private final JwtAuthenticationEntryPoint entryPoint;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                .exceptionHandling(ex ->
//                        ex.authenticationEntryPoint(entryPoint))
//
//                .authorizeHttpRequests(auth -> auth
//
//                        .requestMatchers(
//                                "/auth/**",
//                                "/actuator/**"
//                        ).permitAll()
//
//                        .anyRequest().authenticated())
//
//                .addFilterBefore(jwtFilter,
//                        UsernamePasswordAuthenticationFilter.class)
//
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//}