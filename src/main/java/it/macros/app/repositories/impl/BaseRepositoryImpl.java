package it.macros.app.repositories.impl;

import javax.persistence.*;

import it.macros.app.repositories.BaseCustomRepository;

public abstract class BaseRepositoryImpl implements BaseCustomRepository {
	@PersistenceContext
	protected EntityManager entityManager = null;

	/**
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}