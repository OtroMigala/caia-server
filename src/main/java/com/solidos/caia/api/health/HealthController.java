package com.solidos.caia.api.health;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.auth.enums.RoleEnumClass;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/health")
@PreAuthorize("permitAll()")
public class HealthController {

  @GetMapping
  public String health() {
    return "OK";
  }

  @GetMapping("/check-auth")
  @PreAuthorize("hasAnyRole('" + RoleEnumClass.ORGANIZER + "')")
  public String check() {
    return "OK";
  }

  @PostMapping("/check-auth")
  public String check2() {
    return "OK";
  }
}
