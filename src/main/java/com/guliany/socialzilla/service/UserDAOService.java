package com.guliany.socialzilla.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guliany.socialzilla.entity.User;
import com.guliany.socialzilla.exception.service.NotFoundException;
import com.guliany.socialzilla.exception.service.ServiceException;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<User>();
	private static long usersCount = 3;
	
	static {
		users.add(new User(1l, "Adam", "adam@gmail.com", new Date()));
		users.add(new User(2l, "Eve", "eve@yahoo.com", new Date()));
		users.add(new User(3l, "Jack", "jack@microsoft.com", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(long id) throws ServiceException {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new NotFoundException("User not Found");
	}
	
	public User deleteUser(long id) throws ServiceException {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		throw new NotFoundException("User not Found");
	}
	
	public User addUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

}
