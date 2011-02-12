package no.nuppers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COMMENT_ID", nullable = false)
	private Long commentId;

	@NotNull
	private String comment;
	
	@NotNull
	private String user;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_DATE")
	private Date commentDate;

	public Long getCommentId() {
		return commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
