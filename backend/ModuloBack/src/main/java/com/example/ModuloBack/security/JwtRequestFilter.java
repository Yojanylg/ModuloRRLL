package com.example.ModuloBack.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter
public class JwtRequestFilter  extends OncePerRequestFilter {

    private final String SECRET_KEY = "tu_clave_secreta"; // Cambia esto por una clave secreta segura

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = request.getHeader("Authorization"); // Token en el header

        if (token != null && token.startsWith("Bearer ")) {
            try {
                // Extraemos el token
                String jwtToken = token.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(jwtToken)
                        .getBody();

                // Establecemos la autenticación
                String username = claims.getSubject();
                if (username != null) {
                    SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(username));
                }
            } catch (Exception e) {
                System.out.println("Token JWT no válido");
            }
        }

        filterChain.doFilter(request, response); // Pasamos la solicitud al siguiente filtro

    }
}
