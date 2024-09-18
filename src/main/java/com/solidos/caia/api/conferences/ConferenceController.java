package com.solidos.caia.api.conferences;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.common.models.CommonResponse;
import com.solidos.caia.api.conferences.dto.CreateConferenceDto;
import com.solidos.caia.api.conferences.entities.ConferenceEntity;
import com.solidos.caia.api.members.MembersPermissions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/conferences")
@PreAuthorize("denyAll()")
public class ConferenceController {

  ConferenceService conferenceService;
  MembersPermissions membersPermissions;

  public ConferenceController(ConferenceService conferenceService, MembersPermissions membersPermissions) {
    this.conferenceService = conferenceService;
    this.membersPermissions = membersPermissions;
  }

  @PostMapping
  @PreAuthorize("authenticated")
  public ResponseEntity<CommonResponse<ConferenceEntity>> postMethodName(
      @RequestBody CreateConferenceDto createConferenceDto) {

    ConferenceEntity newConference = conferenceService.createConference(createConferenceDto);

    var commonResponse = CommonResponse.<ConferenceEntity>builder()
        .status(HttpStatus.CREATED.value())
        .message("Conference created successfully")
        .data(newConference)
        .build();

    return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);

  }

}
