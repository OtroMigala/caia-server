package com.solidos.caia.api.members.dto;

import com.solidos.caia.api.common.enums.RoleEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMemberDto {

  @NotBlank
  @NotNull(message = "Role cannot be null")
  private RoleEnum role;

  @NotNull(message = "User cannot be null")
  private Long userId;

  @NotNull(message = "Conference cannot be null")
  private Long conferenceId;
}
