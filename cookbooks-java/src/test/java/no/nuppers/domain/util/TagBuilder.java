package no.nuppers.domain.util;

import no.nuppers.domain.Tag;

public class TagBuilder extends Builder<Tag> {

	private String tagName;

	public TagBuilder tagName(String value) {
		this.tagName = value;
		return this;
	}

	@Override
	public Tag build() {
		Tag tag = new Tag();
		tag.setTagName(tagName);
		return tag;
	}

}
