package no.nuppers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "RECIPE_ID", nullable = false)
	private Long recipeId;

	@NotNull
	private String name;

	@NotNull
	private int page;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="COOKBOOK_ID", nullable = false)
	private Cookbook cookbook;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "RECIPE_TAG", 
		joinColumns = @JoinColumn(name = "RECIPE_ID"),
		inverseJoinColumns = @JoinColumn(name = "TAG_NAME"))
	private List<Tag> tags = new ArrayList<Tag>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RECIPE_ID", nullable = false)
	private List<Comment> comments = new ArrayList<Comment>();

	@Range(min = 1, max = 5)
	private Integer rating;

	public Long getRecipeId() {
		return recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Cookbook getCookbook() {
		return cookbook;
	}

	public void setCookbook(Cookbook cookbook) {
		cookbook.addRecipe(this);
	}

	void setCookbookInternal(Cookbook cookbook) {
		this.cookbook = cookbook;
	}

	public List<Tag> getTags() {
		return tags;
	}
	
	public void addTag(Tag tag) {
		getTags().add(tag);
		tag.getRecipes().add(this);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		getComments().add(comment);
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
