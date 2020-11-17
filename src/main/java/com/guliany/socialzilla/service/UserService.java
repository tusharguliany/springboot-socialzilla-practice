package com.guliany.socialzilla.service;

import java.util.List;

import com.guliany.socialzilla.entity.Post;
import com.guliany.socialzilla.entity.User;
import com.guliany.socialzilla.exception.service.ServiceException;

public interface UserService {
	public List<User> getAllUsers() throws ServiceException;
	public User getUser(long id) throws ServiceException;
	public User deleteUser(long id) throws ServiceException;
	public User addUser(User user);
	public List<Post> getPostsByUser(long id) throws ServiceException;
	public Post addPost(long id, Post post) throws ServiceException;
}
