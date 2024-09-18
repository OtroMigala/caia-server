package com.solidos.caia.api.members;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.api.common.enums.RoleEnum;
import com.solidos.caia.api.common.utils.GetSecurityContext;
import com.solidos.caia.api.members.entities.MemberComposeId;
import com.solidos.caia.api.members.entities.MemberEntity;
import com.solidos.caia.api.members.repositories.MemberRepository;
import com.solidos.caia.api.members.repositories.RoleRepository;
import com.solidos.caia.api.users.UserService;

@Service
public class MembersPermissions {
  private MemberRepository memberRepository;
  private UserService userService;
  private RoleRepository roleRepository;

  public MembersPermissions(MemberRepository memberRepository, UserService userService, RoleRepository roleRepository) {
    this.memberRepository = memberRepository;
    this.userService = userService;
    this.roleRepository = roleRepository;
  }

  public Long hasConferencePermission(Long conferenceId, RoleEnum role) {
    String userEmail = GetSecurityContext.getEmail();

    Long userId = userService.findIdByEmail(userEmail);

    if (userId == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    Long roleId = roleRepository.findRoleId(role);

    if (roleId == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");
    }

    Optional<MemberEntity> member = memberRepository.findById(
        MemberComposeId.builder()
            .userId(userId)
            .conferenceId(conferenceId)
            .roleId(roleId)
            .build());

    if (!member.isPresent()) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized");
    }

    if (member.get().getRoleEntity().getRole() != role) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized");
    }

    return userId;
  }
}
