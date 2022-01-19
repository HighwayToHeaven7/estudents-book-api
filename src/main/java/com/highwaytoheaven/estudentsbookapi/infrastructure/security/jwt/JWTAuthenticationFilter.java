package com.highwaytoheaven.estudentsbookapi.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import static com.highwaytoheaven.estudentsbookapi.infrastructure.security.jwt.JWTConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        super.setFilterProcessesUrl(URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return  authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getEmail(),
                                                        user.getPassword(),
                                                        new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        String token = JWT.create().withSubject(((User) authResult.getPrincipal()).getUsername())
                        .withClaim(ROLE, ((User) authResult.getPrincipal()).getRole().toString())
                        .withClaim(ID, ((User) authResult.getPrincipal()).getId().toString())
                        .withExpiresAt(new Date(System.currentTimeMillis() + TIME))
                        .sign(Algorithm.HMAC512(SECRET_KEY.getBytes()));

        String email = ((User) authResult.getPrincipal()).getUsername();

        String userDtoAsString = authService.createAuthDtoAndConvertToJson(email, token);

        response.setContentType("application/json");
        response.getWriter().write(userDtoAsString);
        response.getWriter().flush();
    }
}
