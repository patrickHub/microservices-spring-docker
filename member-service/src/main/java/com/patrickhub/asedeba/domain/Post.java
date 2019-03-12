package com.patrickhub.asedeba.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Post")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="postID")
	private Long id;
	
	@NotNull
	@Column(name="postName")
	private String name;
	
	@NotNull
	@Column(name="postDescription")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
