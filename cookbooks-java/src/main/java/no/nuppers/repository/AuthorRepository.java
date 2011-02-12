package no.nuppers.repository;

import no.nuppers.domain.Author;

public interface AuthorRepository {

	Author findAuthorById(Long id);
	
}
