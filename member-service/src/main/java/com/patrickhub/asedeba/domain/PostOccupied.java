package com.patrickhub.asedeba.domain;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PostOccupied")
public class PostOccupied {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="postOccupiedID")
	private Long id;
	
	@ManyToOne
	@Column(name="postOccupiedMemberID")
	private Member member;
	
	@ManyToOne
	@Column(name="postOccupiedPostID")
	private Post post;
	
	@NotNull
	@Column(name="postOccupiedStartDate")
	private Date startDate;
	
	@NotNull
	@Column(name="postOccupiedEndDate")
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
	
	
}
