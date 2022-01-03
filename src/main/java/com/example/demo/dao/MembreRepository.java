package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
	Membre findByCin(String cin);
	List<Membre> findByNomStartingWith(String caractere);
	Membre findByEmail(String email);
	@Transactional
	@Modifying
    @Query(value = "UPDATE membre SET membre.type_mbr = ?1 WHERE membre.id = ?2",nativeQuery = true)
	void updateType(String type ,Long id);
	@Query(value = "SELECT * from membre WHERE membre.encadreur_id is null and membre.type_mbr='etd'",nativeQuery = true)
	List<Etudiant> getEtudiantsNonEncadrees();
}
