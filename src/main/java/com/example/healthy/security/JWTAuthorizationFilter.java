package com.example.healthy.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//OncePerRequestFilter c'est a dire qu'in va etre executer une seue fois
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");

        if (jwt == null || !jwt.startsWith(SecurityParameters.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = jwt.substring(SecurityParameters.TOKEN_PREFIX.length());
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParameters.SECRET)).build();
        DecodedJWT decodedJwt = verifier.verify(jwt);
        String username = decodedJwt.getSubject();

        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            List<String> roles = decodedJwt.getClaim("roles").asList(String.class);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
