package com.raissi.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.raissi.domain.User;

@Named("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	@Override
	public User getUserByLoginPassword(String login, String pwd) {
		try {
			Query query = entityManager
					.createQuery("from User user where user.login=:login and user.password=:pwd")
					.setParameter("login", login).setParameter("pwd", pwd);
			return (User) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public User findUserByLoginOrEmail(String loginOrEmail) {
		try {
			Query query = entityManager
					.createQuery("from User user where user.login=:login or user.email=:email")
					.setParameter("login", loginOrEmail).setParameter("email", loginOrEmail);
			return (User) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsers(int first, int pageSize) {
		Query query = entityManager
		.createQuery("from User user where user.role='USER'").setFirstResult(first).setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int countCandidates() {
		Query query = entityManager
		.createQuery("select count(*) from User user where user.role='USER'");
		return ((Long) query.getSingleResult()).intValue();
	}

}
