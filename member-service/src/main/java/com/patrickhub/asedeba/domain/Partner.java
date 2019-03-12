package com.patrickhub.asedeba.domain;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Partner")
public class Partner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="partnerID")
	private Long id;
	
	@NotNull
	@Column(name="partnerName")
	private String name;
	
	@Column(name="partnerContactPerson")
	private String contactPerson;
	
	@Column(name="partnerEmail")
	private String email;
	
	@NotNull
	@Column(name = "partnerPhone")
	private String phone;
	
	@NotNull
	@Column(name="partnerCity")
	private String city;
	
	@NotNull
	@Column(name="partnerCountry")
	private String country;
	
	@NotNull
	@Column(name="partnerInscriptionDate")
	private Date inscriptionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}
	
	
	
	
	

}
