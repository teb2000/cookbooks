package no.nuppers.domain.util;

import java.util.ArrayList;
import java.util.List;

import no.nuppers.domain.Author;
import no.nuppers.domain.Cookbook;

public class CookbookBuilder extends Builder<Cookbook> {

	private String title;
	private List<Author> authors = new ArrayList<Author>();

	public CookbookBuilder title(String value) {
		this.title = value;
		return this;
	}

	public CookbookBuilder authors(List<Author> value) {
		this.authors = value;
		return this;
	}

	@Override
	public Cookbook build() {
		Cookbook cookbook = new Cookbook();
		cookbook.setTitle(title);
		for (Author author : authors) {
			cookbook.addAuthor(author);
		}
		return cookbook;
	}

}
