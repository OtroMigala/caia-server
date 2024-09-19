package com.solidos.caia.api.users.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String affiliation;

  @Column(nullable = false)
  private String password;

  private String token;

  @Builder.Default
  @Column(name = "is_enabled", columnDefinition = "boolean default false", nullable = false)
  private Boolean isEnabled = false;

  @Builder.Default
  @Column(name = "account_no_expired", columnDefinition = "boolean default false", nullable = false)
  private Boolean accountNoExpired = false;

  @Builder.Default
  @Column(name = "account_no_locked", columnDefinition = "boolean default false", nullable = false)
  private Boolean accountNoLocked = false;

  @Builder.Default
  @Column(name = "credentials_no_expired", columnDefinition = "boolean default false", nullable = false)
  private Boolean credentialsNoExpired = false;

  // @OneToMany(targetEntity = MemberEntity.class, fetch = FetchType.LAZY,
  // mappedBy = "userEntity")
  // @JsonManagedReference
  // private List<MemberEntity> members;
}
