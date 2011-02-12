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
import javax.persistence.OneToMany;

@Entity
public class Cookbook {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COOKBOOK_ID", nullable = false)
	private Long cookbookId;
	
	private String title;

	@ManyToMany
	@JoinTable(
		name = "COOKBOOK_AUTHOR", 
		joinColumns=@JoinColumn(name="COOKBOOK_ID"),
	    inverseJoinColumns=@JoinColumn(name="AUTHOR_ID"))
	private List<Author> authors = new ArrayList<Author>();
	
	@OneToMany(mappedBy = "cookbook", cascade = CascadeType.ALL)
	private List<Recipe> recipes = new ArrayList<Recipe>();

	public Long getCookbookId() {
		return cookbookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		getAuthors().add(author);
		author.getCookbooks().add(this);
	}
	
	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void addRecipe(Recipe recipe) {
		getRecipes().add(recipe);
		recipe.setCookbookInternal(this);
	}

}
