package com.learn.spring.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test1")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test1")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test1")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.authorizeHttpRequests(configure ->
               configure
                       .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                       .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                       .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.DELETE   , "/api/employees/**").hasRole("ADMIN")
                );

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
