package com.raissi.service;

import java.io.UnsupportedEncodingException;
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

	/**
	 * Will generate a complete url to the specified pageName and containing the tokenToBeEncrypted as encrypted param,
	 * ex: http://mysite.com/confirm-registration?token=userNameEncrypted 
	 * @param pageName
	 * @param tokenToBeEncrypted
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	String generateUserToken(String pageName, String tokenToBeEncrypted)
			throws UnsupportedEncodingException;

	User findUserByEncryptedLoginOrEmail(String encrypted);
}
