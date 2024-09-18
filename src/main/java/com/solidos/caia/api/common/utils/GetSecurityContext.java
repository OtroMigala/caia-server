package com.solidos.caia.api.common.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetSecurityContext {
  public static String getEmail() {
    SecurityContext authentication = SecurityContextHolder.getContext();
    String userEmail = authentication.getAuthentication().getPrincipal().toString();

    return userEmail;
  }
}
