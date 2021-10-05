package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.MembreRepository;

@SpringBootApplication
public class MemberService implements CommandLineRunner {

	@Autowired
	MembreRepository membreRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheurRepository;
	public static void main(String[] args) {
		SpringApplication.run(MemberService.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		membreRepository.deleteById(1L);
		
	}
	

}
