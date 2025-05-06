package com.example.ModuloRH.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Deshabilitar CSRF, ya que será una API REST
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/empleado/login", "/empleado/registro").permitAll() // Login y registro públicos
                                .requestMatchers("/empleado/**").hasRole("USER")  // Sólo usuarios autenticados para rutas de empleado
                                .requestMatchers("/admin/**").hasRole("ADMIN")  // Sólo admin para rutas de administrador
                                .anyRequest().authenticated()  // Requiere autenticación para cualquier otra ruta
                )
                .formLogin(form -> form
                        .loginPage("/empleado/login")  // Ruta personalizada para login (puedes usar la default de Spring)
                        .permitAll()  // Permite a todos acceder al login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Ruta para el logout
                        .permitAll()  // Permite a todos acceder al logout
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usar BCrypt para encriptar contraseñas
    }
}
