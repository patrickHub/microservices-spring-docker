package com.patrickhub.asedeba.domain;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Project")
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="projectID")
	private Long id;
	
	@NotNull
	@Column(name="projectName")
	private String name;
	
	@ManyToOne
	@Column(name="projectManagerID")
	private Member manager;
	
	@NotNull
	@Column(name="projectStartDate")
	private Date startDate;
	
	@NotNull
	@Column(name="projectEndDate")
	private Date endDate;
	
	@Column(name="projectDescription")
	private String description;

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

	public Member getManager() {
		return manager;
	}

	public void setManager(Member manager) {
		this.manager = manager;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
