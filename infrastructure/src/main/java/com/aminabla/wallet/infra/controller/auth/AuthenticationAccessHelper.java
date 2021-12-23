package com.aminabla.wallet.infra.controller.auth;

import org.springframework.security.core.Authentication;

public interface AuthenticationAccessHelper {
    Authentication getAuthentication();
}
