package com.penguin.esms.utils;

import com.penguin.esms.security.CustomStaffDetail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    @Value("${jwt.JWT_SECRET}")
    private String signature;

    @Value("${jwt.JWT_EXPIRATION}")
    private long expiration;

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        CustomStaffDetail userDetail = (CustomStaffDetail) authentication.getPrincipal();
        return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, signature).compact();
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, signature).compact();
    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signature)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
