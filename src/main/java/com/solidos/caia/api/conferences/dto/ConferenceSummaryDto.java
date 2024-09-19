package com.solidos.caia.api.conferences.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceSummaryDto {
  private Long id;
  private String name;
  private String description;
  private String slug;
  private LocalDateTime createdAt;
}
