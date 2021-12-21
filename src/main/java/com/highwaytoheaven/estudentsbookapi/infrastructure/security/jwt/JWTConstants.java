package com.highwaytoheaven.estudentsbookapi.infrastructure.security.jwt;

public class JWTConstants {
    public static final String URL = "/users/auth";
    public static final String SECRET_KEY = "f-103F15%!f4h8A;s";
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final int TIME = 900_000;
    public static final String ROLE = "role";
    public static final String ID = "uuid";
}
