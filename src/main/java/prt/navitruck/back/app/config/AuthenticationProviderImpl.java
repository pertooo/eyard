/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prt.navitruck.back.app.config;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import prt.navitruck.back.app.domain.AuthTokenImpl;

import prt.navitruck.back.app.domain.UserSession;
import prt.navitruck.back.app.service.RedisService;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AuthenticationProviderImpl implements org.springframework.security.authentication.AuthenticationProvider {

    private RedisService service;

    public AuthenticationProviderImpl(RedisService service) {
        this.service = service;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        if (username == null || username.length() < 5) {
            throw new BadCredentialsException("Username not found.");
        }
        if (password.length() < 5) {
            throw new BadCredentialsException("Wrong password.");
        }

        //Right now just authenticate on the basis of the user=pass
        if (username.equalsIgnoreCase(password)) {
            UserSession u = new UserSession();
            u.setUsername(username);
            u.setCreated(new Date());
            AuthTokenImpl auth = new AuthTokenImpl(u.getUsername(), Collections.emptyList());
            auth.setAuthenticated(true);
            auth.setDetails(u);

            String oldToken = service.getValue(String.format("%s:%s", u.getUsername().toLowerCase(), auth.getHash()));

            if(oldToken!= null){
                auth.setDetails("fuck token Same");
                System.out.println("oldToken exists in redis " );
                return auth;
            }

            service.setValue(String.format("%s:%s", u.getUsername().toLowerCase(), auth.getHash()), u, TimeUnit.SECONDS, 3600L, true);
            System.out.println("redisKey " + String.format("%s:%s", u.getUsername().toLowerCase(), auth.getHash()));
            System.out.println("auth.getHash() " + auth.getHash());
            return auth;
        } else {

        }
        return null;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
