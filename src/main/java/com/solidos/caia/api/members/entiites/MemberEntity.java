package com.solidos.caia.api.members.entiites;

import com.solidos.caia.api.auth.entities.RoleEntity;
import com.solidos.caia.api.common.entiites.AuditMetadata;
import com.solidos.caia.api.conferences.entities.ConferenceEntity;
import com.solidos.caia.api.users.entities.UserEntity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

  @EmbeddedId
  private MemberComposeId memberComposeId;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity usersEntity;

  @ManyToOne
  @JoinColumn(name = "conference_id", insertable = false, updatable = false)
  private ConferenceEntity conferenceEntity;

  @ManyToOne
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private RoleEntity roleEntity;

  @Embedded
  private AuditMetadata auditMetadata;
}
