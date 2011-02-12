package no.nuppers.repository.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import no.nuppers.domain.Author;
import no.nuppers.domain.Cookbook;
import no.nuppers.domain.Cookbook_;
import no.nuppers.repository.CookbookRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaCookbookRepository implements CookbookRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void saveNewCookbook(Cookbook cookbook) {
		entityManager.persist(cookbook);
	}

	@Override
	public Cookbook findCookbookByTitle(String title) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cookbook> criteriaQuery = builder.createQuery(Cookbook.class);
		Root<Cookbook> cookbook = criteriaQuery.from(Cookbook.class);
		criteriaQuery.where(builder.equal(cookbook.get(Cookbook_.title), title));
		return entityManager.createQuery(criteriaQuery).getResultList().get(0);
	}

	@Override
	public List<Cookbook> findCookbooksByAuthor(Author author) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cookbook> criteriaQuery = builder.createQuery(Cookbook.class);
		Root<Cookbook> cookbook = criteriaQuery.from(Cookbook.class);
		criteriaQuery.where(builder.isMember(author, cookbook.get(Cookbook_.authors)));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
