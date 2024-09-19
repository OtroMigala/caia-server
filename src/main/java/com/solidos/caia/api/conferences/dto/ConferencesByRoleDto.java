package com.solidos.caia.api.conferences.dto;

import com.solidos.caia.api.common.enums.RoleEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferencesByRoleDto {
  private Long userId;
  private RoleEnum role;
  private String query;
  private Integer page;
  private Integer offSet;
}
