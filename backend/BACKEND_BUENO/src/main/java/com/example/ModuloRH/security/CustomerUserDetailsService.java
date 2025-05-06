package com.example.ModuloRH.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerUserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
