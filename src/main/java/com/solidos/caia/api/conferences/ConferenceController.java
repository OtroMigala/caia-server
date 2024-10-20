package com.solidos.caia.api.conferences;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.auth.AuthService;
import com.solidos.caia.api.common.enums.RoleEnum;
import com.solidos.caia.api.common.models.CommonResponse;
import com.solidos.caia.api.conferences.dto.ConferenceSummaryDto;
import com.solidos.caia.api.conferences.dto.ConferencesByRoleDto;
import com.solidos.caia.api.conferences.dto.CreateConferenceDto;
import com.solidos.caia.api.conferences.entities.ConferenceEntity;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conferences")
@PreAuthorize("authenticated")
public class ConferenceController {

  private final ConferenceService conferenceService;
  private final AuthService authService;

  public ConferenceController(ConferenceService conferenceService, AuthService authService) {
    this.conferenceService = conferenceService;
    this.authService = authService;
  }


  @GetMapping("/{idOrSlug}")
  @PreAuthorize("permitAll()")
  public ResponseEntity<CommonResponse<ConferenceEntity>> getConference(@PathVariable String idOrSlug) {
    var conference = conferenceService.findByIdOrSlug(idOrSlug);

    var commonResponse = CommonResponse.<ConferenceEntity>builder()
        .status(HttpStatus.OK.value())
        .message("Conference found")
        .data(conference)
        .build();

    return ResponseEntity.ok(commonResponse);
  }

  @GetMapping("/by-role/{role}")
  public ResponseEntity<CommonResponse<List<ConferenceSummaryDto>>> findConferencesByRole(
      @PathVariable @Valid RoleEnum role,
      @RequestParam @Nullable String query,
      @RequestParam @Nullable Integer page,
      @RequestParam @Nullable Integer offSet) {
    Long userId = authService.getUserIdByEmail();

    ConferencesByRoleDto conferencesByRoleDto = ConferencesByRoleDto
        .builder()
        .query(query)
        .page(page)
        .offSet(offSet)
        .role(role)
        .userId(userId)
        .build();

    var commonResponse = CommonResponse.<List<ConferenceSummaryDto>>builder()
        .status(HttpStatus.OK.value())
        .message("Conferences found")
        .data(conferenceService.findConferencesByRole(conferencesByRoleDto))
        .build();

    return ResponseEntity.ok(commonResponse);
  }

  @GetMapping
  @PreAuthorize("permitAll()")
  public ResponseEntity<CommonResponse<List<ConferenceSummaryDto>>> findAllConferences(
      @RequestParam @Nullable String query,
      @RequestParam @Nullable Integer page,
      @RequestParam @Nullable Integer offSet) {

    var commonResponse = CommonResponse.<List<ConferenceSummaryDto>>builder()
        .status(HttpStatus.OK.value())
        .message("Conferences found")
        .data(conferenceService.findAllConferences(query, page, offSet))
        .build();

    return ResponseEntity.ok(commonResponse);
  }

  @PostMapping
  @PreAuthorize("hasRole('ORGANIZER')")
  public ResponseEntity<CommonResponse<ConferenceEntity>> createConference(@Valid @RequestBody CreateConferenceDto createConferenceDto) {
      ConferenceEntity newConference = conferenceService.createConference(createConferenceDto);
      
      var commonResponse = CommonResponse.<ConferenceEntity>builder()
          .status(HttpStatus.CREATED.value())
          .message("Conference created successfully")
          .data(newConference)
          .build();

      return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
  }
}
