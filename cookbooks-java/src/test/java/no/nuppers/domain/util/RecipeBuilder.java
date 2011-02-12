package no.nuppers.domain.util;

import java.util.ArrayList;
import java.util.List;

import no.nuppers.domain.Comment;
import no.nuppers.domain.Cookbook;
import no.nuppers.domain.Recipe;
import no.nuppers.domain.Tag;

public class RecipeBuilder extends Builder<Recipe> {

	private String name;
	private int page;
	private Integer rating;
	private Cookbook cookbook;
	private List<Tag> tags = new ArrayList<Tag>();
	private List<Comment> comments = new ArrayList<Comment>();

	public RecipeBuilder name(String value) {
		this.name = value;
		return this;
	}

	public RecipeBuilder page(int value) {
		this.page = value;
		return this;
	}

	public RecipeBuilder rating(Integer value) {
		this.rating = value;
		return this;
	}
	
	public RecipeBuilder cookbook(Cookbook value) {
		this.cookbook = value;
		return this;
	}

	public RecipeBuilder tags(List<Tag> value) {
		this.tags = value;
		return this;
	}

	public RecipeBuilder comments(List<Comment> value) {
		this.comments = value;
		return this;
	}

	@Override
	public Recipe build() {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setPage(page);
		recipe.setRating(rating);
		recipe.setCookbook(cookbook);
		for (Tag tag : tags) {
			recipe.addTag(tag);
		}
		for (Comment comment : comments) {
			recipe.addComment(comment);
		}
		return recipe;
	}

}
