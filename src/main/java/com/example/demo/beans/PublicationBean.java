package com.example.demo.beans;

import java.util.Date;

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
	private String lien;
	private Date date;
	private String sourcePDF;
}
