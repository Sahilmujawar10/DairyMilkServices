package com.project.dairyproject.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class FeedBackDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FID;

	@Nullable
	@Length(min = 3, max = 18, message = "First name can be in between 3 to 18 characters only")
	@Pattern(regexp = "^[a-zA-Z ]{0,18}$", message = "Please enter your correct name")
	private String name;

	@Length(max = 50, message = "Subject cannot be more than 30 characters !")
	private String subject;

	@NotEmpty
	@Lob
	private String message;

	public int getFID() {
		return FID;
	}

	public void setFID(int fID) {
		FID = fID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
