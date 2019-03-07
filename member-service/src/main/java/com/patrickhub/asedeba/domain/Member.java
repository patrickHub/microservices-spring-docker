package com.patrickhub.asedeba.domain;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Member")
public class Member {

	    @Id
	    @Column(name = "memberID")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "memberFirstName")
	    private String firstName;
	    
	    @NotNull
	    @Column(name = "memberLastName")
	    private String lastName;
	    
	    @NotNull
	    @Column(name = "memberBirthdate")
	    private Date birthdate;
	    
	    
	    @Column(name = "memberEmail")
	    private String email;
	    
	    @NotNull
	    @Column(name = "memberPhone")
	    private String phone;
	    
	    @Column(name = "memberOriginVillage")
	    private String originVillage;
	    
	    @Column(name = "memberInscriptionDate")
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





		public String getOriginVillage() {
			return originVillage;
		}





		public void setOriginVillage(String originVillage) {
			this.originVillage = originVillage;
		}





		public Date getInscriptionDate() {
			return inscriptionDate;
		}





		public void setInscriptionDate(Date inscriptionDate) {
			this.inscriptionDate = inscriptionDate;
		}





		@Override
		public String toString() {
			return "Member [id=" + id + ", firsName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
					+ ", email=" + email + ", phone=" + phone + ", originVillage=" + originVillage
					+ ", inscriptionDate=" + inscriptionDate + "]";
		}

	
}
