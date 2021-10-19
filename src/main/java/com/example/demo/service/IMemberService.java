package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;

public interface IMemberService {
	// Crud sur les membres
	Membre addMember(Membre m);

	void deleteMember(Long id);

	Membre updateMember(Membre p);

	Membre findMember(Long id);

	List<Membre> findAll();

	// Filtrage par propriété
	Membre findByCin(String cin);

	Membre findByEmail(String email);

	List<Membre> findByNom(String nom);

	// recherche spécifique des étudiants
	List<Etudiant> findByDiplome(String diplome);

	// recherche spécifique des enseignants
	List<EnseignantChercheur> findByGrade(String grade);

	List<EnseignantChercheur> findByEtablissement(String etablissement);
	
	Etudiant affecterEtudiantAEnseignant(Long idEtd,Long idEns);
	List<Etudiant> getEtudiantsDeEnseignant(EnseignantChercheur enseignantChercheur);

}
