package no.nuppers.domain.util;

import java.util.Date;

import no.nuppers.domain.Comment;

public class CommentBuilder extends Builder<Comment> {

	private String comment;
	private String user;
	private Date commentDate;

	public CommentBuilder comment(String value) {
		this.comment = value;
		return this;
	}

	public CommentBuilder user(String value) {
		this.user = value;
		return this;
	}

	public CommentBuilder commentDate(Date value) {
		this.commentDate = value;
		return this;
	}

	@Override
	public Comment build() {
		Comment c = new Comment();
		c.setComment(comment);
		c.setUser(user);
		c.setCommentDate(commentDate);
		return c;
	}

}
