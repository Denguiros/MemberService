package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.MembreEvenement;
import com.example.demo.entities.MembrePublication;
import com.example.demo.entities.PublicationMembreId;

@Repository
public interface MemberPublicationRepository extends JpaRepository<MembrePublication, PublicationMembreId> {
	@Query("select m from MembrePublication m where auteur_id=:x")
	List<MembrePublication> findMemberPublicationByAuteurId(@Param("x") Long auteurId);
	@Query("select m from MembrePublication m where publication_id=:x")
	List<MembrePublication> findCollabsOfPublication(@Param("x") Long publicationId);
	@Transactional
	@Modifying
	@Query(value = "DELETE from Membre_Publication where auteur_id=?1 and publication_id=?2",nativeQuery = true)
	void desaffecterAuteurFromPublication(Long id, Long pubId);
}
