package com.BlogApp.Payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int categoryId;
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@NotBlank
	@Size(min = 4)
     private String categoryDetails;
	@NotBlank
	@Size(min = 4)
	 private String description;
	public int getCategoryId() {
		return categoryId;
	}
	public CategoryDto(int categoryId, String categoryDetails, String description) {
		super();
		this.categoryId = categoryId;
		this.categoryDetails = categoryDetails;
		this.description = description;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryDetails() {
		return categoryDetails;
	}
	public void setCategoryDetails(String categoryDetails) {
		this.categoryDetails = categoryDetails;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
