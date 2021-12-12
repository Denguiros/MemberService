package com.example.demo.service;

import java.util.List;

import com.example.demo.beans.EvenementBean;
import com.example.demo.beans.OutilBean;
import com.example.demo.beans.PublicationBean;
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
	void affecterAuteurToPublication(Long idAuteur, Long idPublication);
	List<PublicationBean> findPublicationsByAuteur (Long idAuteur);
	void affecterParticipantToEvenement(Long idParticipant, Long idEvenement);
	List<EvenementBean> findEvenementByParticipant (Long idParticipant);
	void affecterMembreToOutil(Long idMembre, Long idOutil);
	List<OutilBean> findOutilByMembre (Long idMembre);
}
