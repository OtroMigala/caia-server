package com.solidos.caia.api.conferences;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solidos.caia.api.common.enums.RoleEnum;
import com.solidos.caia.api.common.utils.SlugGenerator;
import com.solidos.caia.api.conferences.dto.CreateConferenceDto;
import com.solidos.caia.api.conferences.entities.ConferenceEntity;
import com.solidos.caia.api.conferences.repositories.ConferenceRepository;
import com.solidos.caia.api.members.MemberService;
import com.solidos.caia.api.members.dto.CreateMemberDto;
import com.solidos.caia.api.users.UserService;

@Service
public class ConferenceService {
  private ConferenceRepository conferenceRepository;
  private MemberService memberService;
  private UserService userService;

  public ConferenceService(
      ConferenceRepository conferenceRepository,
      MemberService memberService,
      UserService userService) {
    this.conferenceRepository = conferenceRepository;
    this.memberService = memberService;
    this.userService = userService;
  }

  @Transactional
  public ConferenceEntity createConference(CreateConferenceDto createConferenceDto) {
    SecurityContext authentication = SecurityContextHolder.getContext();
    String userEmail = authentication.getAuthentication().getPrincipal().toString();

    Long userId = userService.findIdByEmail(userEmail);

    ConferenceEntity newConference = conferenceRepository.save(
        ConferenceEntity.builder()
            .name(createConferenceDto.getName())
            .description(createConferenceDto.getDescription())
            .slug(SlugGenerator.generate(createConferenceDto.getName()))
            .build());

    memberService.createMember(
        CreateMemberDto.builder()
            .role(RoleEnum.ORGANIZER)
            .userId(userId)
            .conferenceId(newConference.getId())
            .build());

    return newConference;
  }
}
