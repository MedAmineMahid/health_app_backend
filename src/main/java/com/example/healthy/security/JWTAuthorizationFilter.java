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
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //on va recevoir le token a partir du header qu'on a nommé Authorization dans JWTAuthentication
        String jwt = request.getHeader("Authorization");
        if (jwt == null && !jwt.startsWith(SecurityParameters.TOKEN_PREFIX)) {

            filterChain.doFilter(request, response);
            return;

        }
        //decouper jwt
        //supprimer prefix
        jwt = jwt.substring(SecurityParameters.TOKEN_PREFIX.length());
        //verifier celon l'algo et mot de passe
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParameters.SECRET)).build();
        DecodedJWT decodedJwt = verifier.verify(jwt);
        String username = decodedJwt.getSubject();
        List<String> roles = decodedJwt.getClaims().get("roles").asList(String.class);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            filterChain.doFilter(request, response);
        }

    }
}
