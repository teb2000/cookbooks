package no.nuppers.repository;

import java.util.List;

import no.nuppers.domain.Recipe;
import no.nuppers.domain.Tag;

public interface RecipeRepository {

	List<Recipe> findRecipesByTag(Tag tag);
	
	List<Recipe> findRecipesWithNameLike(String name);
	
}
