package com.BlogApp.Payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDto {
	  @Id
	  @GeneratedValue(strategy =GenerationType.AUTO)
	  private int id; 
	  @NotNull
	  private String name;
	  @Email
	  private String email;
	  @NotNull
	  private String password;
	  @NotNull
	  private String about;
	  public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public UserDto() {
		super();
	
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	  

}
