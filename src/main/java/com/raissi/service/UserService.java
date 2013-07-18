package com.raissi.service;

import java.util.List;

import com.raissi.domain.User;

public interface UserService {
	
	public User loginUser(String login, String password);

	/**
	 * Method to get candidates
	 * @return List of candidates
	 */
	public List<User> getCandidates(int page, int pageSize);
	
	public void saveUser(User user);
	
	public User findUserByLoginOrEmail(String loginOrEmail);
	
	public void updateUser(User user);
	
	public int countCandidates();
	
	public User validateUser(String token);
}
