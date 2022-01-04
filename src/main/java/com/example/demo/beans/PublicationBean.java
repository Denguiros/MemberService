package com.example.demo.beans;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationBean {
	private long id;
	private String type;
	private String titre;
	private String photo;
	private String description;
	private String lien;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String sourcePDF;
}
