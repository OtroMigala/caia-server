package com.solidos.caia.api.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {
  @NotNull(message = "First name cannot be null")
  String firstName;

  @NotNull(message = "Last name cannot be null")
  String lastName;

  @NotNull(message = "Email cannot be null")
  @Email(message = "Invalid email")
  String email;

  @NotNull(message = "Afiliation cannot be null")
  @Size(min = 5, max = 255, message = "Afiliation must be between 5 and 255 characters")
  String afiliation;

  @NotNull(message = "Password cannot be null")
  @Size(min = 6, message = "Password must be at least 6 characters")
  String password;
}
