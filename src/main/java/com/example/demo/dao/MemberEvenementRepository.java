package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.EvenementMembreId;
import com.example.demo.entities.MembreEvenement;
import com.example.demo.entities.MembrePublication;
import com.example.demo.entities.PublicationMembreId;

@Repository
public interface MemberEvenementRepository extends JpaRepository<MembreEvenement, EvenementMembreId> {
	@Query("select m from MembreEvenement m where participant_id=:x")
	List<MembreEvenement> findEvenementByParticipantId(@Param("x") Long participantId);
	@Query("select m from MembreEvenement m where evenement_id=:x")
	List<MembreEvenement> findParticipantsOfEvenement(@Param("x") Long evenementId);
	@Transactional
	@Modifying
	@Query(value = "DELETE from Membre_Evenement where participant_id=?1 and evenement_id=?2",nativeQuery = true)
	void desaffecterParticipantFromEvenement(Long id, Long eveId);
}
