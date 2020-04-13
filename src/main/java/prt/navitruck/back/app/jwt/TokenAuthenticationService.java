/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.navitruck.back.app.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Sha512DigestUtils;
import prt.navitruck.back.app.domain.AuthTokenImpl;
import prt.navitruck.back.app.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class TokenAuthenticationService {

    private RedisService service;

    private long EXPIRATIONTIME = 1000 * 60 * 60; // 1 hr

    private String secret;

    private String tokenPrefix = "Bearer";

    private String headerString = "Authorization";

    
    
    public TokenAuthenticationService(RedisService service, String key) {
        this.service = service;
        secret = Sha512DigestUtils.shaHex(key);
    }

    public void addAuthentication(HttpServletResponse response, AuthTokenImpl auth) {
        // We generate a token now.
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", auth.getPrincipal());
        claims.put("hash", auth.getHash());
        String JWT = Jwts.builder()
                .setSubject(auth.getPrincipal().toString())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        response.addHeader(headerString, tokenPrefix + " " + JWT);


        service.setValue( JWT, auth.getPrincipal(), TimeUnit.SECONDS, EXPIRATIONTIME, true);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (token == null) {
            return null;
        }
        //remove "Bearer" text
        token = token.replace(tokenPrefix, "").trim();

        //Validating the token
        if (!token.isEmpty()) {
            // parsing the token.`
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token).getBody();

            } catch ( Exception e) {
                return null;
            }
            
            //Valid token and now checking to see if the token is actally expired or alive by quering in redis.
            if (claims != null && claims.containsKey("username")) {
                String username = claims.get("username").toString();
                String hash = claims.get("hash").toString();

             //   SessionUser user = (SessionUser) service.getValue(String.format("%s:%s", username,hash), SessionUser.class);
                String user =  service.getValue(token);

                if (user != null) {
                    AuthTokenImpl auth = new AuthTokenImpl(username, Collections.emptyList());
                    auth.authenticate();
                    return auth;
                } else {
                    return new UsernamePasswordAuthenticationToken(null, null);
                }

            }
        }
        return null;
    }
}
