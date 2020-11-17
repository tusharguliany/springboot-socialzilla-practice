package com.guliany.socialzilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.guliany.socialzilla.entity.Post;
import com.guliany.socialzilla.entity.User;
import com.guliany.socialzilla.exception.AppException;
import com.guliany.socialzilla.exception.service.ServiceException;
import com.guliany.socialzilla.service.UserDAOService;
import com.guliany.socialzilla.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private MessageSource messageSource;

//	@Autowired
//	private UserDAOService userDAOService;
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping("/")
	public String getGreeting() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	// Returns all users
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() throws AppException {
		return new ResponseEntity<>(this.service.getAllUsers(), HttpStatus.OK);
//		return new ResponseEntity<>(this.userDAOService.findAll(), HttpStatus.OK);
	}
	
	// Adds User and returns the new user object
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		return new ResponseEntity<>(this.service.addUser(user), HttpStatus.CREATED);
//		return new ResponseEntity<>(this.userDAOService.addUser(user), HttpStatus.CREATED);
	}
	
	// finds user by id and returns it
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable long id) throws AppException {
		User user = this.service.getUser(id);
//		User user = this.userDAOService.findOne(id);
		EntityModel<User> resource = EntityModel.of(user);
		
		// hateaos principles
		// Generates links for getting all users and delete user with id
		WebMvcLinkBuilder linkToAllUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		WebMvcLinkBuilder linkToDeleteUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUserById(id));
		
		// adds links to response object
		resource.add(linkToAllUsers.withRel("all-users"));
		resource.add(linkToDeleteUser.withRel("delete user"));
		
		return resource;
	}
	
	// with Filtering POC
//	@GetMapping("/users/{id}")
//	public MappingJacksonValue getUserById(@PathVariable long id) throws AppException {
//		User user = this.userDAOService.findOne(id);
//		EntityModel<User> resource = EntityModel.of(user);
//		
//		// hateaos principles
//		// Generates links for getting all users and delete user with id
//		WebMvcLinkBuilder linkToAllUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
//		WebMvcLinkBuilder linkToDeleteUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUserById(id));
//		
//		// adds links to response object
//		resource.add(linkToAllUsers.withRel("all-users"));
//		resource.add(linkToDeleteUser.withRel("delete user"));
//		
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "email", "birthDate", "href");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("User Filter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(user);
//		mapping.setFilters(filters);
//		
//		return mapping;
//	}
	
	// deletes user by id
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable long id) throws AppException {
		return new ResponseEntity<>(this.service.deleteUser(id), HttpStatus.OK);
//		return new ResponseEntity<>(this.userDAOService.deleteUser(id), HttpStatus.OK);
	}
	
	/* 
	 * API's related to User Posts
	*/

	// gets all posts made by user with id path variable
	@GetMapping("/users/{id}/posts")
	public ResponseEntity<?> getPostsByUser(@PathVariable long id) throws AppException {
		return new ResponseEntity<>(this.service.getPostsByUser(id), HttpStatus.OK);
	}
	
	// creates post with user id as path variable
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<?> createPost(@PathVariable long id, @RequestBody Post post) throws AppException {
		return new ResponseEntity<>(this.service.addPost(id, post), HttpStatus.CREATED);
	}
	
}
