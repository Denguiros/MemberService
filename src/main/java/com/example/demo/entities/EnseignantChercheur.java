package com.example.demo.entities;


import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ens")
public class EnseignantChercheur extends Membre {
	private static final long serialVersionUID = 1L;
	private String grade;
	private String etablissement;
}
