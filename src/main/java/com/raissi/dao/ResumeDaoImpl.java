package com.raissi.dao;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.raissi.domain.Resume;

@Named("resumeDao")
public class ResumeDaoImpl  extends BaseDaoImpl implements ResumeDao{

	@Override
	public Resume getResumeByUser(Long userId) {
		try {
		Query query = entityManager
		.createQuery("from Resume res where res.id = " +
				"(select user.resume.id from User user where user.userId = :userId)").setParameter("userId", userId);
		return (Resume) query.getSingleResult();
		}catch (NoResultException ex) {
			return null;
		}
	}

}
