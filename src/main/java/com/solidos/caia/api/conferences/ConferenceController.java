package com.solidos.caia.api.conferences;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.common.models.CommonResponse;
import com.solidos.caia.api.conferences.dto.CreateConferenceDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/conferences")
@PreAuthorize("denyAll()")
public class ConferenceController {

  @PostMapping
  @PreAuthorize("authenticated")
  public ResponseEntity<CommonResponse<CreateConferenceDto>> postMethodName(
      @RequestBody CreateConferenceDto createConferenceDto) {

    var commonResponse = CommonResponse.<CreateConferenceDto>builder()
        .status(HttpStatus.CREATED.value())
        .message("Conference created successfully")
        .data(createConferenceDto)
        .build();

    return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
  }

}
