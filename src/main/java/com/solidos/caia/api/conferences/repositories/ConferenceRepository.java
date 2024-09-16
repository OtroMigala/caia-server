package com.solidos.caia.api.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidos.caia.api.conferences.entities.ConferenceEntity;

@Repository
public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {

}
