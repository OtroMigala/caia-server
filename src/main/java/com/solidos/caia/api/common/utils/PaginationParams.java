package com.solidos.caia.api.common.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PaginationParams {
  private Pageable pageable = Pageable.unpaged();
  private String query = null;

  public PaginationParams(Pageable pageable, String query) {
    this.pageable = pageable;
    this.query = query;
  }

  public static Pageable of(Integer page, Integer offSet) {
    if (page == null) {
      page = 0;
    }

    if (offSet == null) {
      offSet = 10;
    }

    return PageRequest.of(page, offSet);
  }

  public static PaginationParams withQuery(Integer page, Integer offSet, String query) {
    Pageable pageable = of(page, offSet);

    query = clearQuery(query);

    return new PaginationParams(pageable, query);
  }

  public static String clearQuery(String query) {
    if (query == null) {
      return null;
    }
    return URLDecoder.decode(query, StandardCharsets.UTF_8).toLowerCase();
  }
}
