package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationMembreId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long publicationId;
	private Long auteurId;

}
