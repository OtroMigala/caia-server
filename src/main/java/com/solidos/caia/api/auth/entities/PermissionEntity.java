package com.solidos.caia.api.auth.entities;

import com.solidos.caia.api.auth.enums.AuthoritiesEnum;
import com.solidos.caia.api.common.entities.AuditMetadata;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "permission", nullable = false)
  @Enumerated(EnumType.STRING)
  private AuthoritiesEnum permission;

  @Embedded
  private AuditMetadata auditMetadata;

}
