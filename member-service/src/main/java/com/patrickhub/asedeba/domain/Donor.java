package com.patrickhub.asedeba.domain;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="Donor")
public class Donor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DonorID")
	private Long id;
	
	@Column(name="donorFirstName")
	private String firstName;
	
	@NotNull
	@Column(name="donorLastName")
	private String lastName;
	
	@NotNull
	@Column(name="donorBirthdate")
	private Date birthdate;
	
	@Column(name="donorEmail")
	private String email;
	
	@NotNull
	@Column(name="donorPhone")
	private String phone;
	
	@NotNull
	@Column(name="donorCity")
	private String city;
	
	@NotNull
	@Column(name="donorCountry")
	private String country;
	
	@NotNull
	@Column(name="donorInscriptionDate")
	private Date inscriptionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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
