package no.nuppers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "AUTHOR_ID", nullable = false)
	private Long authorId;
	
	@NotNull
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	
	@NotNull
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@ManyToMany(mappedBy = "authors")
	private List<Cookbook> cookbooks = new ArrayList<Cookbook>();

	public Long getAuthorId() {
		return authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Cookbook> getCookbooks() {
		return cookbooks;
	}

	public void addCookbook(Cookbook cookbook) {
		getCookbooks().add(cookbook);
		cookbook.getAuthors().add(this);
	}
	
}
