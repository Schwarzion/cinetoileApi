package com.cinetoile.SpringAPI.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    public UserDetailsImpl getPrincipalCurrentUser() {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) getAuthentication().getPrincipal();
        return userPrincipal;
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}