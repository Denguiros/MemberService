package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.EvenementBean;
import com.example.demo.beans.OutilBean;
import com.example.demo.beans.PublicationBean;
import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MemberEvenementRepository;
import com.example.demo.dao.MemberOutilRepository;
import com.example.demo.dao.MemberPublicationRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.EvenementMembreId;
import com.example.demo.entities.Membre;
import com.example.demo.entities.MembreEvenement;
import com.example.demo.entities.MembreOutil;
import com.example.demo.entities.MembrePublication;
import com.example.demo.entities.OutilMembreId;
import com.example.demo.entities.PublicationMembreId;
import com.example.demo.proxies.EvenementProxyService;
import com.example.demo.proxies.OutilProxyService;
import com.example.demo.proxies.PublicationProxyService;

@Service
public class MemberImpl implements IMemberService {

	@Autowired
	MembreRepository memberRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheurRepository;
	@Autowired
	MemberPublicationRepository memberPublicationRepository;
	@Autowired
	MemberEvenementRepository memberEvenementRepository;
	@Autowired
	MemberOutilRepository memberOutilRepository;
	@Autowired
	PublicationProxyService publicationProxyService;
	@Autowired
	EvenementProxyService evenementProxyService;
	@Autowired
	OutilProxyService outilProxyService;
	@Override
	public Membre addMember(Membre m) {
		memberRepository.save(m);
		return m;
	}

	@Override
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}

	@Override
	public Membre updateMember(Membre m) {
		return memberRepository.saveAndFlush(m);
	}

	@Override
	public Membre findMember(Long id) {
		return (Membre) memberRepository.findById(id).get();
	}

	@Override
	public List<Membre> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public Membre findByCin(String cin) {
		return memberRepository.findByCin(cin);
	}

	@Override
	public Membre findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	@Override
	public List<Membre> findByNom(String nom) {
		return memberRepository.findByNomStartingWith(nom);
	}

	@Override
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}

	@Override
	public List<EnseignantChercheur> findByGrade(String grade) {
		return enseignantChercheurRepository.findByGrade(grade);
	}

	@Override
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
		return enseignantChercheurRepository.findByEtablissement(etablissement);
	}

	@Override
	public Etudiant affecterEtudiantAEnseignant(Long idEtd, Long idEns) {
		Etudiant e = etudiantRepository.findById(idEtd).get();
		EnseignantChercheur ens = enseignantChercheurRepository.findById(idEns).get();
		e.setEncadreur(ens);
		return memberRepository.saveAndFlush(e);
	}

	@Override
	public List<Etudiant> getEtudiantsDeEnseignant(EnseignantChercheur enseignantChercheur) {
		return etudiantRepository.findByEncadreur(enseignantChercheur);
	}

	@Override
	public void affecterAuteurToPublication(Long idAuteur, Long idPublication) {

		Membre mbr = memberRepository.findById(idAuteur).get();
		MembrePublication mbs = new MembrePublication();
		mbs.setAuteur(mbr);
		mbs.setId(new PublicationMembreId(idPublication, idAuteur));
		memberPublicationRepository.save(mbs);

	}

	@Override
	public List<PublicationBean> findPublicationsByAuteur(Long idAuteur) {
		List<PublicationBean> pubs = new ArrayList<PublicationBean>();
		List<MembrePublication> idpubs = memberPublicationRepository.findMemberPublicationByAuteurId(idAuteur);
		idpubs.forEach(s -> {
			System.out.println(s);
			pubs.add(publicationProxyService.findPublicationById(s.getId().getPublicationId()));
		});
		return pubs;

	}

	@Override
	public void affecterParticipantToEvenement(Long idParticipant, Long idEvenement) {
		Membre mbr = memberRepository.findById(idParticipant).get();
		MembreEvenement mbe = new MembreEvenement();
		mbe.setParticipant(mbr);
		mbe.setId(new EvenementMembreId(idEvenement, idParticipant));
		memberEvenementRepository.save(mbe);
	}

	@Override
	public List<EvenementBean> findEvenementByParticipant(Long idParticipant) {
		List<EvenementBean> events = new ArrayList<EvenementBean>();
		List<MembreEvenement> idEvents = memberEvenementRepository.findEvenementByParticipantId(idParticipant);
		idEvents.forEach(s -> {
			System.out.println(s);
			events.add(evenementProxyService.findEvenementById(s.getId().getEvenementId()));
		});
		return events;

	}

	@Override
	public void affecterMembreToOutil(Long idMembre, Long idOutil) {
		Membre mbr = memberRepository.findById(idMembre).get();
		MembreOutil mbo = new MembreOutil();
		mbo.setMembre(mbr);
		mbo.setId(new OutilMembreId(idOutil, idMembre));
		memberOutilRepository.save(mbo);
	}

	@Override
	public List<OutilBean> findOutilByMembre(Long idMembre) {
		List<OutilBean> outils = new ArrayList<OutilBean>();
		List<MembreOutil> idEvents = memberOutilRepository.findMemberOutilByMemberId(idMembre);
		idEvents.forEach(s -> {
			System.out.println(s);
			outils.add(outilProxyService.findOutilById(s.getId().getOutilId()));
		});
		return outils;
	}
}