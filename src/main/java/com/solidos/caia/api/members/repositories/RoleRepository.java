package com.solidos.caia.api.members.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.api.common.enums.RoleEnum;
import com.solidos.caia.api.members.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  RoleEntity findByRole(RoleEnum role);

  @Query("SELECT r.id FROM RoleEntity r WHERE r.role = ?1")
  Long findRoleId(RoleEnum role);
}
