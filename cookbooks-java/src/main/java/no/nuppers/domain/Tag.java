package no.nuppers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Tag {

	@NotNull
	@Id
	@Column(name = "TAG_NAME", nullable = false)
	private String tagName;

	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
	private List<Recipe> recipes = new ArrayList<Recipe>();

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}
}
