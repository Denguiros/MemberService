package com.example.demo.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutilBean {
	private long id;
	private Date date;
	private String nom;
	private boolean open;
	private String codeSource;
}
