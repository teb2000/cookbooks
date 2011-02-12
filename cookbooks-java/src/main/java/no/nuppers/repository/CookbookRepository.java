package no.nuppers.repository;

import java.util.List;

import no.nuppers.domain.Author;
import no.nuppers.domain.Cookbook;

public interface CookbookRepository {

	void saveNewCookbook(Cookbook cookbook);
	
	Cookbook findCookbookByTitle(String title);
	
	List<Cookbook> findCookbooksByAuthor(Author author);
}
