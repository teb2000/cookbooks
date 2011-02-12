package no.nuppers.repository.support;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nuppers.domain.Author;
import no.nuppers.repository.AuthorRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaAuthorRepository implements AuthorRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Author findAuthorById(Long id) {
		return entityManager.find(Author.class, id);
	}

}
