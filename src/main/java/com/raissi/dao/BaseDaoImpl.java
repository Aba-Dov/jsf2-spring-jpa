package com.raissi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl implements BaseDao{
	
	protected EntityManager entityManager;

	@Override
	public void save(Object o) {
		entityManager.persist(o);
	}

	@Override
	public void update(Object o) {
		entityManager.merge(o);
	}

	@Override
	public void delete(Object o) {
		entityManager.remove(o);
	}
	
	@PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
