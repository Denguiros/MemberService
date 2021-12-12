package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.MembrePublication;
import com.example.demo.entities.PublicationMembreId;

@Repository
public interface MemberPublicationRepository extends JpaRepository<MembrePublication, PublicationMembreId> {
	@Query("select m from MembrePublication m where auteur_id=:x")
	List<MembrePublication> findMemberPublicationByAuteurId(@Param("x") Long auteurId);
}
