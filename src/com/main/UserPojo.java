package com.main;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class UserPojo {

	@Persistent
	private String id;
	@PrimaryKey
	@Persistent
	private String email;
	@Persistent
	private String verified_email;
	@Persistent
	private String name;
	@Persistent
	private String given_name;
	@Persistent
	private String family_name;
	@Persistent
	private String link;
	@Persistent
	private String picture;
	@Persistent
	private String gender;
	@Persistent
	private String locale;
	@Persistent
	private String hd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerified_email() {
		return verified_email;
	}

	public void setVerified_email(String verified_email) {
		this.verified_email = verified_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	@Override
	public String toString() {
		return "PersonalData [id=" + id + ", email=" + email + ", verified_email=" + verified_email + ", name=" + name
				+ ", given_name=" + given_name + ", family_name=" + family_name + ", link=" + link + ", picture="
				+ picture + ", gender=" + gender + ", locale=" + locale + ", hd=" + hd + "]";
	}
}
