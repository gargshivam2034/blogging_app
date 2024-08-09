package com.BlogApp.Services;

import java.util.List;

import com.BlogApp.Payloads.CategoryDto;

public interface CategoryService {
	public CategoryDto  createCategory(CategoryDto userDto);
	public CategoryDto updateCategory(CategoryDto userDto,Integer id);
	public CategoryDto getCategoryById(Integer id);
	public List<CategoryDto> getAllCategory();
	void  deleteCategory(Integer id);

}
