package no.nuppers.repository.support;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import no.nuppers.domain.Cookbook;
import no.nuppers.domain.Recipe;
import no.nuppers.domain.Tag;
import no.nuppers.domain.util.CommentBuilder;
import no.nuppers.domain.util.CookbookBuilder;
import no.nuppers.domain.util.RecipeBuilder;
import no.nuppers.domain.util.TagBuilder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRecipeRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private JpaRecipeRepository repository;
	
	@Test
	public void shouldFindRecipeByTag() throws Exception {
		Tag tag = new TagBuilder().tagName("Beef").build();
		String recipeName = "Boeuf Bourguignon";
		new RecipeBuilder()
			.cookbook(createCookbook())
			.name(recipeName)
			.page(300)
			.rating(5)
			.tags(Arrays.asList(
			tag	
			))
			.comments(Arrays.asList(
			new CommentBuilder()
				.comment("This was delicious")
				.user("Thomas")
				.commentDate(new Date())
				.build()
			))
			.buildAndPersist(entityManager);

		
		List<Recipe> recipes = repository.findRecipesByTag(tag);

		assertThat(recipes.size(), is(1));
		assertThat(recipes.get(0).getName(), is(recipeName));
	}

	@Test
	public void shouldFindRecipeByCompleteName() throws Exception {
		String recipeName = "Boeuf Bourguignon";
		Recipe recipe = new RecipeBuilder()
								.cookbook(createCookbook())
								.name(recipeName)
								.page(300)
								.rating(5)
								.buildAndPersist(entityManager);

		List<Recipe> recipes = repository.findRecipesWithNameLike(recipeName);
		
		assertThat(recipes.size(), is(1));
		assertThat(recipes.get(0).getRecipeId(), is(recipe.getRecipeId()));		
	}
	
	@Test
	public void shouldFindRecipeByPartialName() throws Exception {
		String recipeName = "Boeuf Bourguignon";
		Recipe recipe = new RecipeBuilder()
								.cookbook(createCookbook())
								.name(recipeName)
								.page(300)
								.rating(5)
								.buildAndPersist(entityManager);
		
		List<Recipe> recipes = repository.findRecipesWithNameLike("Boeuf");
		
		assertThat(recipes.size(), is(1));
		assertThat(recipes.get(0).getRecipeId(), is(recipe.getRecipeId()));		
	}
	
	private Cookbook createCookbook() {
		return new CookbookBuilder()
					.title("Mastering the Art of French Cooking")
					.build();
	}
	
}
