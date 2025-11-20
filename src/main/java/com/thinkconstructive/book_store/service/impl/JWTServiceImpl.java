package com.thinkconstructive.book_store.service.impl;
import com.thinkconstructive.book_store.service.JWTService;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
@Service
public class JWTServiceImpl implements JWTService {
    private String secretKey="";
// logic to get secret key
    public JWTServiceImpl() throws NoSuchAlgorithmException {
        KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256");
        SecretKey secret=keygen.generateKey();
        secretKey=Base64.getEncoder().encodeToString(secret.getEncoded());
    }
    @Override
    public String generateToken(String username) {
        HashMap<String, Object> claims = new HashMap<String, Object>();

        return Jwts.builder()
                .claims()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
                .and()
                .signWith(getKey())
                .compact();

    }
    //logic to get ket
    private SecretKey getKey() {
        byte[] keyValue= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyValue);
    }
}
