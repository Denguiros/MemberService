package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.service.IMemberService;

@SpringBootApplication
@EnableDiscoveryClient
public class MemberService implements CommandLineRunner {

	@Autowired
	MembreRepository membreRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheurRepository;
	@Autowired
	IMemberService memberService;
	public static void main(String[] args) {
		SpringApplication.run(MemberService.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*EnseignantChercheur ens1 = new EnseignantChercheur("Docteur","Enis");
		EnseignantChercheur ens2 = new EnseignantChercheur("Nouveau","Enis");
		Etudiant e1 = new Etudiant();
		e1.setNom("Fehmi");
		Etudiant e2 = new Etudiant();
		e2.setNom("Taha");
		memberService.addMember(ens1);
		memberService.addMember(ens2);
		memberService.addMember(e1);
		memberService.addMember(e2);
		memberService.updateMember(memberService.affecterEtudiantAEnseignant(3L, 1L));
		memberService.updateMember(memberService.affecterEtudiantAEnseignant(4L, 1L));
		List<Etudiant> etudiantsDeEns1 = memberService.getEtudiantsDeEnseignant((EnseignantChercheur)memberService.findMember(1L));
		System.out.println("Les etudiants de l'encadrant 1 =>");
		for (Etudiant etudiant : etudiantsDeEns1) {
			System.out.println(etudiant.getNom());
		}*/
	}
	

}
