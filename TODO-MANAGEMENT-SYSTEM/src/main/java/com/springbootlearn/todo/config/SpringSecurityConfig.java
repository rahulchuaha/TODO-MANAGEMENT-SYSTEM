package com.springbootlearn.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) ->{
            // authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
            // authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
            // authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
            // authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
            // authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
            // authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails rahul = User.builder()
                            .username("rahul")
                            .password(passwordEncoder().encode("Rahul@123"))
                            .roles("USER")
                            .build();
        UserDetails admin = User.builder()
                            .username("admin")
                            .password(passwordEncoder().encode("Admin@123"))
                            .roles("ADMIN")
                            .build();
            return new InMemoryUserDetailsManager(rahul, admin);
        }
    
}
