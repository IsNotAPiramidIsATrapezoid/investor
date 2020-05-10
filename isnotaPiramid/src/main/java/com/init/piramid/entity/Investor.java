package com.init.piramid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="investors")
public class Investor {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="document", nullable = false, length = 20) 
	private long document;
	
	@Column(name="name", nullable = false, length = 60) 
	private String name;
	
	@Column(name="mail", length = 60) 
	private String mail;
	
	@Column(name="investments") 
	private long investments;
	
	@Column(name="profile") 
	private String profile;
	
	
	public long getDocument() {
		return document;
	}
	public void setDocument(long document) {
		this.document = document;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public long getInvestments() {
		return investments;
	}
	public void setInvestments(long investments) {
		this.investments = investments;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
