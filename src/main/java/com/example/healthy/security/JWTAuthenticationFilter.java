package com.example.healthy.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.healthy.security.entities.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;



    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        try {
            //ObjectMapper sert a sérialiser et désérialiser les objet vers JSON
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        }catch (JsonParseException e){
            e.printStackTrace();
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    }


    //creation du JWT token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        user.getAuthorities().forEach(role -> roles.add(role.getAuthority()));
        String jwt = JWT.create()
                .withSubject(user.getUsername())
                .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParameters.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SecurityParameters.SECRET));
        response.addHeader("Authorization",jwt);
    }


}
