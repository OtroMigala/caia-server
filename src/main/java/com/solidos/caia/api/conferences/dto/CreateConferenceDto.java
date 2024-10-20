package com.solidos.caia.api.conferences.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateConferenceDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name must be at most 255 characters long")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description must be at most 1000 characters long")
    private String description;
}