package no.nuppers.repository.support;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import no.nuppers.domain.Author;
import no.nuppers.domain.Cookbook;
import no.nuppers.domain.util.AuthorBuilder;
import no.nuppers.domain.util.CommentBuilder;
import no.nuppers.domain.util.CookbookBuilder;
import no.nuppers.domain.util.RecipeBuilder;
import no.nuppers.domain.util.TagBuilder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JpaCookbookRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private JpaCookbookRepository repository;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void shouldSaveNewCookBook() throws Exception {

		Author author = new AuthorBuilder()
							.firstName("Julia")
							.lastName("Child")
							.buildAndPersist(entityManager);
		
		Cookbook cookbook = new CookbookBuilder()
								.title("Mastering the Art of French Cooking")
								.authors(Arrays.asList(author))
								.build();
		
		new RecipeBuilder()
					.cookbook(cookbook)
					.name("Boeuf Bourguignon")
					.page(300)
					.rating(5)
					.tags(Arrays.asList(
						new TagBuilder().tagName("Beef").build(),	
						new TagBuilder().tagName("Stew").build()	
						))
					.comments(Arrays.asList(
						new CommentBuilder()
							.comment("This was delicious")
							.user("Thomas")
							.commentDate(new Date())
							.build()
						))
					.build();
		
		repository.saveNewCookbook(cookbook);
		
		entityManager.flush();
		
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(dataSource);
		
		assertThat(jdbcTemplate.queryForInt("select count(*) from COOKBOOK"), is(1)); 
		assertThat(jdbcTemplate.queryForInt("select count(*) from RECIPE"), is(1)); 
		assertThat(jdbcTemplate.queryForInt("select count(*) from COMMENT"), is(1)); 
		assertThat(jdbcTemplate.queryForInt("select count(*) from TAG"), is(2)); 
		
	}
	
	@Test
	public void shouldFindCookbookByTitle() throws Exception {
		String title = "Mastering the Art of French Cooking";
		new CookbookBuilder()
				.title(title)
				.buildAndPersist(entityManager);
		
		Cookbook result = repository.findCookbookByTitle(title);
		
		assertThat(result.getTitle(), is(title));
	}
	
	@Test
	public void shouldFindCookbookByAuthor() throws Exception {
		Author author = new AuthorBuilder()
							.firstName("Julia")
							.lastName("Child")
							.buildAndPersist(entityManager);

		Cookbook cookbook = new CookbookBuilder()
								.title("Mastering the Art of French Cooking")
								.authors(Arrays.asList(author))
								.buildAndPersist(entityManager);
		
		Cookbook cookbook2 = new CookbookBuilder()
								.title("Mastering the Art of French Cooking vol 2")
								.authors(Arrays.asList(author))
								.buildAndPersist(entityManager);
		
		List<Cookbook> result = repository.findCookbooksByAuthor(author);
		
		assertThat(result.size(), is(2));
		assertThat(result.get(0).getCookbookId(), is(cookbook.getCookbookId()));
		assertThat(result.get(1).getCookbookId(), is(cookbook2.getCookbookId()));
	}
	
}
