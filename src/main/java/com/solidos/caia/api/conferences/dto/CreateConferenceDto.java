package com.solidos.caia.api.conferences.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateConferenceDto {
  @NotBlank(message = "Name cannot be blank")
  @NotNull(message = "Name cannot be null")
  private String name;

  @NotBlank(message = "Description cannot be blank")
  @NotNull(message = "Description cannot be null")
  @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
  private String description;
}
