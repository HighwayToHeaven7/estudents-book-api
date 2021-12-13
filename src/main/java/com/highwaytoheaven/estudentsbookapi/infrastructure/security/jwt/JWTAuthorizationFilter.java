package com.highwaytoheaven.estudentsbookapi.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String ROLE = "role";
    private final String SECRET_KEY = "f-103F15%!f4h8A;s";

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER);

        if (header == null || !header.startsWith(PREFIX)) {
            chain.doFilter(request, response);
        }
        else {
            SecurityContextHolder.getContext().getAuthentication();
            Optional<UsernamePasswordAuthenticationToken> authentication = getAuthentication(request);

            if (authentication.isEmpty())
                throw new IOException();

            SecurityContextHolder.getContext().setAuthentication(authentication.get());
            chain.doFilter(request, response);
        }
    }

    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        String token = request.getHeader(HEADER);

        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build()
                    .verify(token.replace(PREFIX, ""))
                    .getSubject();

            Claim claim = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build()
                    .verify(token.replace(PREFIX, ""))
                    .getClaim(ROLE);

            if (user != null) {
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null,
                        List.of(new SimpleGrantedAuthority(claim.asString())));
            }
        }
        return Optional.of(usernamePasswordAuthenticationToken);
    }
}
