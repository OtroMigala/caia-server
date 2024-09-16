package com.solidos.caia.api.auth.filters;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.solidos.caia.api.common.utils.JwtHelper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwTokenValidator extends OncePerRequestFilter {

  private JwtHelper jwtHelper;

  public JwTokenValidator(JwtHelper jwtHelper) {
    this.jwtHelper = jwtHelper;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    String jwToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (jwToken == null) {
      filterChain.doFilter(request, response);
      return;
    }

    jwToken = jwToken.replace("Bearer ", "");

    DecodedJWT decodedJwt = jwtHelper.validateToken(jwToken);

    if (decodedJwt == null) {
      filterChain.doFilter(request, response);
      return;
    }

    String email = jwtHelper.extractEmail(decodedJwt);
    String stringAuthorities = jwtHelper.getSpecificClaim(decodedJwt, "authorities").asString();

    Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
        stringAuthorities);

    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

    securityContext.setAuthentication(authentication);

    SecurityContextHolder.setContext(securityContext);

    filterChain.doFilter(request, response);
  }

}
