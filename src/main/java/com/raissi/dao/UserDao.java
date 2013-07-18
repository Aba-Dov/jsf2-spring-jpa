package com.raissi.dao;

import java.util.List;

import com.raissi.domain.User;

public interface UserDao extends BaseDao{

	public User getUserByLoginPassword(String login, String pwd);
	public User findUserByLoginOrEmail(String loginOrEmail);
	public List<User> findUsers(int first, int pageSize);
	public int countCandidates();
}
