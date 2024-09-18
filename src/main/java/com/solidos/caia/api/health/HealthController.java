package com.solidos.caia.api.health;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.api.common.enums.RoleEnum;
import com.solidos.caia.api.members.MembersPermissions;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/health")
@PreAuthorize("permitAll()")
public class HealthController {

  MembersPermissions membersPermissions;

  public HealthController(MembersPermissions membersPermissions) {
    this.membersPermissions = membersPermissions;
  }

  @GetMapping
  public String health() {
    return "OK";
  }

  @GetMapping("/check-auth")
  @PreAuthorize("authenticated")
  public String check(@RequestParam Long conferenceId) {
    Long userId = membersPermissions.hasConferencePermission(conferenceId, RoleEnum.ORGANIZER);

    return "Check Auth OK: userId:" + userId;
  }

  @PostMapping("/check-auth")
  public String check2() {
    return "OK";
  }
}
