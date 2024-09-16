package com.solidos.caia.api.auth.entities;

import java.util.List;

import com.solidos.caia.api.auth.enums.RolesEnum;
import com.solidos.caia.api.common.entiites.AuditMetadata;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private RolesEnum role;

  @Embedded
  private AuditMetadata auditMetadata;

  @OneToMany
  @JoinColumn(name = "role_id")
  private List<PermissionEntity> permissions;
}
