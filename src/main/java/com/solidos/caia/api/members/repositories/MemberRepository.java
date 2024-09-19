package com.solidos.caia.api.members.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.api.members.entities.MemberComposeId;
import com.solidos.caia.api.members.entities.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, MemberComposeId> {

  @Query("SELECT m FROM MemberEntity m WHERE m.memberComposeId.userId = ?1 AND m.auditMetadata.deletedAt IS NULL")
  List<MemberEntity> findAllByUserId(Long userId, Pageable pageable);
}
