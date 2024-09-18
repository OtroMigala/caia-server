package com.solidos.caia.api.members.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidos.caia.api.members.entities.MemberComposeId;
import com.solidos.caia.api.members.entities.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, MemberComposeId> {
}
