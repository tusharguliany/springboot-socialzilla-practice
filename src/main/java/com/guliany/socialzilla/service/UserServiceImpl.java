package com.guliany.socialzilla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guliany.socialzilla.entity.Post;
import com.guliany.socialzilla.entity.User;
import com.guliany.socialzilla.exception.service.NotFoundException;
import com.guliany.socialzilla.exception.service.ServiceException;
import com.guliany.socialzilla.repository.PostRepository;
import com.guliany.socialzilla.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<User> getAllUsers() throws ServiceException {
		return this.userRepository.findAll();
	}

	@Override
	public User getUser(long id) throws ServiceException {
		return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
	}

	@Override
	public User deleteUser(long id) throws ServiceException {
		User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		this.userRepository.delete(user);
		return user;
	}

	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<Post> getPostsByUser(long id) throws ServiceException {
		User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		return user.getPosts();
	}

	@Override
	public Post addPost(long id, Post post) throws ServiceException {
		User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		post.setUser(user);
		return postRepository.save(post);
	}

}
