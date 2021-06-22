package com.wave.jwtSecurity;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.wave.constants.Constants;
import com.wave.modelJwt.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {


    public String generate(JwtUser jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRY_TIME_MILLISECONDS))
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                .compact();
    }
}
