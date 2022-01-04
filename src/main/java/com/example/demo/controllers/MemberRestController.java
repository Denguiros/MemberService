package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.beans.EvenementBean;
import com.example.demo.beans.OutilBean;
import com.example.demo.beans.PublicationBean;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.service.IMemberService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT }, allowedHeaders = "*")
public class MemberRestController {
	@Autowired
	IMemberService memberService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/membres", method = RequestMethod.GET)
	public List<Membre> findMembres() {

		List<Membre> membres = memberService.findAll();
		membres.forEach(member -> {
			List<PublicationBean> listeOfPublications = memberService.findPublicationsByAuteur(member.getId());
			List<EvenementBean> listOfEvents = memberService.findEvenementByParticipant(member.getId());
			List<OutilBean> listOfOutils = memberService.findOutilByMembre(member.getId());
			member.setPublications(listeOfPublications);
			member.setEvenements(listOfEvents);
			member.setOutils(listOfOutils);
		});
		return membres;
	}
	@GetMapping(value = "/enseignant/{id}/etudiants")
	public List<Etudiant> getEtudiantsDeEnseignant(@PathVariable Long id)
	{
		return memberService.getEtudiantsDeEnseignant((EnseignantChercheur) memberService.findMember(id));
	}
	@GetMapping(value = "/etudiantsNonEncadrees")
	public List<Etudiant> getEtudiantsNonEncadrees()
	{
		return memberService.getEtudiantsNonEncadrees();
	}
	@PostMapping(value="etudiant/{idEtd}/affecter/{idEns}")
	public void affecterEtudiantAEnseignant(@PathVariable Long idEtd,@PathVariable Long idEns)
	{
		memberService.affecterEtudiantAEnseignant(idEtd, idEns);
	}
	@DeleteMapping(value="/etudiant/{id}/desaffecter")
	public void desaffecterEtudiantDeEnseignant(@PathVariable Long id)
	{
		memberService.desaffecterEtudiantDeEnseignant(id);
	}
	
	@GetMapping(value = "/membre/{id}")
	public Membre findOneMemberById(@PathVariable Long id) {
		List<PublicationBean> listeOfPublications = memberService.findPublicationsByAuteur(id);
		List<EvenementBean> listOfEvents = memberService.findEvenementByParticipant(id);
		List<OutilBean> listOfOutils = memberService.findOutilByMembre(id);
		Membre member = memberService.findMember(id);
		if (member != null) {
			member.setPublications(listeOfPublications);
			member.setEvenements(listOfEvents);
			member.setOutils(listOfOutils);
		}
		return member;
	}

	@GetMapping(value = "/membre/search/cin")
	public Membre findOneMemberByCin(@RequestParam String cin) {
		return memberService.findByCin(cin);
	}

	@GetMapping(value = "/membre/search/email")
	public Membre findOneMemberByEmail(@RequestParam String email) {
		Membre member = memberService.findByEmail(email);
		if (member != null) {
			List<PublicationBean> listeOfPublications = memberService.findPublicationsByAuteur(member.getId());
			List<EvenementBean> listOfEvents = memberService.findEvenementByParticipant(member.getId());
			List<OutilBean> listOfOutils = memberService.findOutilByMembre(member.getId());
			member.setPublications(listeOfPublications);
			member.setEvenements(listOfEvents);
			member.setOutils(listOfOutils);
		}
		return member;
	}
	@GetMapping(value="/eventParticipants/{id}")
	public List<Membre> getAllEventParticipants(@PathVariable Long id)
	{
		return memberService.getAllEvenetParticipants(id);
	}
	@PostMapping(value = "/membres/enseignant")
	public Membre addMembre(@RequestBody EnseignantChercheur m) {
		return memberService.addMember(m);
	}

	@PostMapping(value = "/membres/etudiant")
	public Membre addMembre(@RequestBody Etudiant e) {
		return memberService.addMember(e);
	}
	@PostMapping(value = "/membre/{id}/publication/{pubId}")
	public void affecterAuteurToPublication(@PathVariable Long id,@PathVariable Long pubId) {
		memberService.affecterAuteurToPublication(id, pubId);
	}
	@PostMapping(value = "/membre/{id}/publication/{pubId}/desaffecter")
	public void desaffecterAuteurToPublication(@PathVariable Long id,@PathVariable Long pubId) {
		memberService.desaffecterAuteurFromPublication(id, pubId);
	}
	@PostMapping(value = "/membre/{id}/evenement/{eveId}")
	public void affecterParticipantToEvenement(@PathVariable Long id,@PathVariable Long eveId) {
		memberService.affecterParticipantToEvenement(id, eveId);
	}
	@PostMapping(value = "/membre/{id}/evenement/{eveId}/desaffecter")
	public void desaffecterParticipantToEvenement(@PathVariable Long id,@PathVariable Long eveId) {
		memberService.desaffecterParticipantFromEvenement(id, eveId);
	}
	@PostMapping(value = "/membre/{id}/outil/{ouId}")
	public void affecterMembreToOutil(@PathVariable Long id,@PathVariable Long ouId) {
		memberService.affecterMembreToOutil(id, ouId);
	}
	@PostMapping(value = "/membre/{id}/outil/{ouId}/desaffecter")
	public void desaffecterMembreFromOutil(@PathVariable Long id,@PathVariable Long ouId) {
		memberService.desaffecterMembreFromOutil(id, ouId);
	}

	@DeleteMapping(value = "/membres/{id}")
	public void deleteMembre(@PathVariable Long id) {
		memberService.deleteMember(id);
	}

	@GetMapping(value = "/get-file")
	@ResponseBody
	public FileSystemResource getFile(@RequestParam(name = "path") String path) {
		String decodedPath = URLDecoder.decode(path);
		System.out.println(decodedPath);
		File f = new File(decodedPath);
		return new FileSystemResource(f.getAbsolutePath());
	}
	@PutMapping(value = "/membres/{id}/type")
	public void updateType(@PathVariable Long id, @RequestParam(name = "type") String type)
	{
		memberService.updateMemberType(type, id);
	}
	@PutMapping(value = "/membres/etudiant/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Membre updateMembre(@PathVariable Long id, @RequestParam(name = "member") String etudiant,
			@RequestParam(name = "cv", required = false) MultipartFile cv,
			@RequestParam(name = "photo", required = false) MultipartFile photo) {
		Membre member = memberService.findMember(id);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibility(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		Membre m = null;
		try {
			m = objectMapper.readValue(etudiant, Etudiant.class);
			if (cv != null) {
				String path = writeFile(m.getNom() + m.getPrenom() + "/cv", cv);
				String encodedPath = "";
				try {
					encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				m.setCv(encodedPath);
			}
			if (photo != null) {
				String path = writeFile(m.getNom() + m.getPrenom() + "/photo", photo);
				String encodedPath = "";
				try {
					encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				m.setPhoto(encodedPath);
			}
			if (m.getCv() == "" || m.getCv() == null) {
				m.setCv(member.getCv());
			}
			
			if (m.getPhoto() == "" || m.getPhoto() == null) {
				m.setPhoto(member.getPhoto());
			}
			m.setId(id);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return memberService.updateMember(m);
	}

	public String writeFile(String name, MultipartFile file) {

		String uploadsDir = "uploads/" + name + "/";
		File f = new File(uploadsDir);
		String absolutePath = f.getAbsolutePath();
		if (!f.exists()) {
			System.out.println("Creating new directory");
			f.mkdirs();
		}
		String orgName = file.getOriginalFilename();
		String filePath = absolutePath + "/" + orgName;
		File dest = new File(filePath);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadsDir + "/" + orgName;
	}

	@PutMapping(value = "/membres/enseignant/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Membre updateEnseignant(@PathVariable Long id, @RequestParam(name = "member") String enseignant,
			@RequestParam(name = "cv", required = false) MultipartFile cv,
			@RequestParam(name = "photo", required = false) MultipartFile photo) {
		Membre member = memberService.findMember(id);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibility(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		Membre m = null;
		try {
			m = objectMapper.readValue(enseignant, EnseignantChercheur.class);
			if (cv != null) {
				String path = writeFile(m.getNom() + m.getPrenom() + "/cv", cv);
				String encodedPath = "";
				try {
					encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				m.setCv(encodedPath);
			}
			if (photo != null) {
				String path = writeFile(m.getNom() + m.getPrenom() + "/photo", photo);
				String encodedPath = "";
				try {
					encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.toString());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				m.setPhoto(encodedPath);
			}
			if (m.getCv() == "" || m.getCv() == null) {
				m.setCv(member.getCv());
			}
			if (m.getPhoto() == "" || m.getPhoto() == null) {
				m.setPhoto(member.getPhoto());
			}
			m.setId(id);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return memberService.updateMember(m);
	}
}