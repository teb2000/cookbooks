package no.nuppers.repository.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import no.nuppers.domain.Recipe;
import no.nuppers.domain.Recipe_;
import no.nuppers.domain.Tag;
import no.nuppers.repository.RecipeRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaRecipeRepository implements RecipeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Recipe> findRecipesByTag(Tag tag) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = builder.createQuery(Recipe.class);
		Root<Recipe> recipe = criteriaQuery.from(Recipe.class);
		criteriaQuery.where(builder.isMember(tag, recipe.get(Recipe_.tags)));
		List<Recipe> recipes = entityManager.createQuery(criteriaQuery).getResultList();
		return recipes;
	}

	@Override
	public List<Recipe> findRecipesWithNameLike(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = builder.createQuery(Recipe.class);
		Root<Recipe> recipe = criteriaQuery.from(Recipe.class);
		criteriaQuery.where(builder.like(recipe.get(Recipe_.name), "%" + name + "%"));
		List<Recipe> recipes = entityManager.createQuery(criteriaQuery).getResultList();
		return recipes;
	}

}
