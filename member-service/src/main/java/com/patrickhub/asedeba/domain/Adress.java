package com.patrickhub.asedeba.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Adress")
public class Adress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="adressID")
	private Long id;
	
	@Column(name="adressStreet")
	private String street;
	
	@Column(name="adressZipCode")
	private String zipCode;
	
	@NotNull
	@Column(name="adressCity")
	private String city;
	
	@NotNull
	@Column(name="adressCountry")
	private String country;
	
	@ManyToOne
	@JoinColumn(name="adressMemberID")
	private Member member;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	

}
