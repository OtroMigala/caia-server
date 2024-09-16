package com.solidos.caia.api.common.utils;

import java.io.IOException;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletResponse;

public class ServletResponse {
  public static void response(int code, String message) throws IOException {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes != null) {
      HttpServletResponse response = attributes.getResponse();
      if (response != null) {
        response.sendError(code, message);
      }
    }
  }
}
