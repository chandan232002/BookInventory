package com.thinkconstructive.book_store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userOne = User.withUsername("One").password(passwordEncoder().encode("one1")).roles("USER").build();
//        UserDetails userTwo = User.withUsername("Two").password(passwordEncoder().encode("two2")).roles("USER").build();
//        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encgode("admin1")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(userOne, userTwo, admin);
//    }
    @Autowired
    public UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrfCustomizer -> csrfCustomizer.disable());//disable CSRF (Cross-Site Request Forgery) protection.
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/book-app/welcome").permitAll().anyRequest().authenticated());
//        http.httpBasic(Customizer.withDefaults());// Use Basic Authentication for simplicity
//        http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//Spring Security will never create an HTTP session and will not use it to get or store the security context (user authentication details).
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfCustomizer -> csrfCustomizer.disable());
        http.authorizeHttpRequests(authorize->authorize.requestMatchers("/book-app/welcome","/user-info/register","/user-info/login").permitAll().anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }
}
