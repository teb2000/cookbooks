package no.nuppers.domain.util;

import javax.persistence.EntityManager;

public abstract class Builder<T> {

	public T buildAndPersist(EntityManager entityManager) {
		T entity = build();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.clear();
		return entity;
	}
	
	public abstract T build();
	
}
