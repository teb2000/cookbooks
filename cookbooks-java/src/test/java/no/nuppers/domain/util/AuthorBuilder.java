package no.nuppers.domain.util;

import no.nuppers.domain.Author;

public class AuthorBuilder extends Builder<Author> {

	private String firstName;
	private String lastName;

	public AuthorBuilder firstName(String value) {
		this.firstName = value;
		return this;
	}

	public AuthorBuilder lastName(String value) {
		this.lastName = value;
		return this;
	}

	@Override
	public Author build() {
		Author author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);
		return author;
	}

}
