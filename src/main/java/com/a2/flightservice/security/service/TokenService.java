package com.a2.flightservice.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;


public interface TokenService {

    String generate(Claims claims);

    Claims parseToken(String jwt);
}
