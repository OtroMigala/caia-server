package com.solidos.caia.api.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.auth.dto.AuthResponse;
import com.solidos.caia.api.auth.dto.LoginDto;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class AuthController {

  private AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Valid LoginDto loginDto) {

    return authService.login(loginDto.getEmail(), loginDto.getPassword());
  }

}
