package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("etd")
public class Etudiant extends Membre {
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dateInscription;
	private String diplome;
	@ManyToOne
	private EnseignantChercheur encadreur;
}
