package com.solidos.caia.api.common.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> {
  private int status;
  private String error;
  private String message;
  private T data;
}
