package no.nuppers.repository.support;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.nuppers.domain.Author;
import no.nuppers.domain.util.AuthorBuilder;
import no.nuppers.repository.support.JpaAuthorRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaAuthorRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private JpaAuthorRepository repository;
	
	@Test
	public void shouldFindAuthorById() throws Exception {
		Author author = new AuthorBuilder()
							.firstName("Jamie")
							.lastName("Oliver")
							.buildAndPersist(entityManager);

		Author result = repository.findAuthorById(author.getAuthorId());
		
		assertThat(result.getFirstName(), is(author.getFirstName()));
		assertThat(result.getLastName(), is(author.getLastName()));
	}
	
}
