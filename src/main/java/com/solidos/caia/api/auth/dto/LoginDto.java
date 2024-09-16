package com.solidos.caia.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
  @NotBlank(message = "Email cannot be blank")
  @NotNull(message = "Email cannot be null")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  @NotNull(message = "Password cannot be null")
  private String password;
}
