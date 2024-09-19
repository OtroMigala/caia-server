package com.solidos.caia.api.users.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.api.users.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u WHERE u.isEnabled = true AND u.email != ?1")
  List<UserEntity> findAllUsers(String email, Pageable pageable);

  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByToken(String token);

  @Query("SELECT u FROM UserEntity u " +
      "WHERE (LOWER(u.email) LIKE %?2% " +
      "OR LOWER(u.firstName) LIKE %?2% " +
      "OR LOWER(u.lastName) LIKE %?2%) " +
      "AND u.isEnabled = true AND u.email != ?1")
  List<UserEntity> findByQuery(String email, String query, Pageable pageable);

  @Query("SELECT u.id FROM UserEntity u WHERE u.email = ?1")
  Optional<Long> findIdByEmail(String email);
}
