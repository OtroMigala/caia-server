package com.solidos.caia.api.conferences;

import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solidos.caia.api.common.enums.RoleEnum;
import com.solidos.caia.api.common.utils.PaginationParams;
import com.solidos.caia.api.common.utils.SlugGenerator;
import com.solidos.caia.api.conferences.apdaptes.ConferenceEntityAdapter;
import com.solidos.caia.api.conferences.dto.ConferenceSummaryDto;
import com.solidos.caia.api.conferences.dto.CreateConferenceDto;
import com.solidos.caia.api.conferences.entities.ConferenceEntity;
import com.solidos.caia.api.conferences.repositories.ConferenceRepository;
import com.solidos.caia.api.members.MemberService;
import com.solidos.caia.api.members.dto.CreateMemberDto;
import com.solidos.caia.api.members.entities.MemberEntity;
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

  public List<ConferenceSummaryDto> findAllConferences(String query, Integer page, Integer offSet) {
    PaginationParams paginationParams = PaginationParams.withQuery(page, offSet, query);

    if (paginationParams.getQuery() == null || paginationParams.getQuery().length() < 3) {
      return conferenceRepository
          .findAll(paginationParams.getPageable())
          .stream()
          .map(ConferenceEntityAdapter::toConferenceSummary)
          .toList();
    }
    System.out.println(paginationParams.getQuery());

    return conferenceRepository
        .findAllConferences(
            paginationParams.getQuery(),
            paginationParams.getPageable())
        .stream()
        .map(ConferenceEntityAdapter::toConferenceSummary)
        .toList();
  }

  public ConferenceEntity findByIdOrSlug(String idOrSlug) {

    Long conferenceId = -1L;
    String slug = idOrSlug;

    try {
      conferenceId = Long.parseLong(idOrSlug);
    } catch (Exception e) {
      slug = idOrSlug;
    }

    return conferenceRepository
        .findByIdOrSlug(conferenceId, slug)
        .orElseThrow(() -> new IllegalArgumentException("Conference not found"));
  }

  public List<ConferenceSummaryDto> findConferencesByRole(Long userId, RoleEnum role) {
    List<MemberEntity> members = memberService.findByRole(userId, role);

    return members.stream()
        .map(m -> ConferenceEntityAdapter.toConferenceSummary(m.getConferenceEntity())).toList();
  }
}
