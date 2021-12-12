package com.example.demo.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvenementBean {
	private long id;
	private String titre;
	private Date date;
	private String lieu;
}
