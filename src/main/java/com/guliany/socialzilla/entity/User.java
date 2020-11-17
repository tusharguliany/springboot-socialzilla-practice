package com.guliany.socialzilla.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@JsonFilter("User Filter")
@ApiModel(description = "All Details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, message = "Name must be at least 2 characters.")
	@ApiModelProperty(notes = "Name should have at least 2 characters")
	private String name;

	@Email(message = "Only Valid Email Addresses Accepted")
	@ApiModelProperty(notes = "Email address has to be valid")
	private String email;

	@Past(message = "Birthdate must be a past time")
	@ApiModelProperty(notes = "Birthdates should be before current time")
	private Date birthDate;

	private Date createdAt;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User() {
		super();
	}

	public User(Long id, String name, String email, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.createdAt = new Date();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", createdAt="
				+ createdAt + "]";
	}

}
