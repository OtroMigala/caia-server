package com.solidos.caia.api.conferences.apdaptes;

import com.solidos.caia.api.conferences.dto.ConferenceSummaryDto;
import com.solidos.caia.api.conferences.entities.ConferenceEntity;

public class ConferenceEntityAdapter {
  public static ConferenceSummaryDto toConferenceSummary(ConferenceEntity conferenceEntity) {
    return ConferenceSummaryDto.builder()
        .id(conferenceEntity.getId())
        .name(conferenceEntity.getName())
        .description(conferenceEntity.getDescription())
        .createdAt(conferenceEntity.getAuditMetadata().getCreatedAt())
        .slug(conferenceEntity.getSlug())
        .build();
  }
}
