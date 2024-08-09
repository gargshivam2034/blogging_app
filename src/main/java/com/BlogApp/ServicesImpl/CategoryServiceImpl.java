package com.BlogApp.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Payloads.CategoryDto;
import com.BlogApp.Respository.CategoryRepo;
import com.BlogApp.Services.CategoryService;
import com.BlogApp.entites.Category;
import com.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category= this.categoryRepo.save(categoryDtoToCategory(categoryDto));
		return modelMapper.map(category,CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
	Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Id", id));
	category.setCategoryDetails(categoryDto.getCategoryDetails());
	category.setDescription(categoryDto.getDescription());
	  Category updateCategory= this.categoryRepo.save(category);
	   return  modelMapper.map(updateCategory, CategoryDto.class);
	
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
	Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Id", id));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category>categories=this.categoryRepo.findAll();
		return categories.stream().map((category)->modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteCategory(Integer id) {
		this.categoryRepo.deleteById(id);
	}
	
	public CategoryDto categoryToCategoryDto(Category category) {
		return modelMapper.map(category,CategoryDto.class);
	}
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, Category.class);
	}
	

}
