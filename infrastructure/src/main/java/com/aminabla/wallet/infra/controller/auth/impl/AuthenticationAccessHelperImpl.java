package com.aminabla.wallet.infra.controller.auth.impl;

import com.aminabla.wallet.infra.controller.auth.AuthenticationAccessHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthenticationAccessHelperImpl implements AuthenticationAccessHelper {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
