package br.com.myapk.domain.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class LoggedTokenUserService {

    public long loggedUser(@AuthenticationPrincipal Jwt jwt){

        var tenant = jwt.getClaims().get("tenant");

        return Long.parseLong(String.valueOf(tenant));
    }
}
