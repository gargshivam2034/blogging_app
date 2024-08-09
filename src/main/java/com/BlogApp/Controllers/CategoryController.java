package com.BlogApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.CategoryDto;
import com.BlogApp.Payloads.UserApiResponse;
import com.BlogApp.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategories(@Valid @RequestBody CategoryDto categoryDto) {		
		CategoryDto createdCategoryDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategoryDto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{categoryId}")
	public  ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
	{
		CategoryDto updatedCategoryDto=this.categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedCategoryDto);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<UserApiResponse> deleteUser(@PathVariable Integer categoryId )
	{
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity(new UserApiResponse("Category deleted successfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
        return  ResponseEntity.ok(this.categoryService.getAllCategory()); 
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId)
	{
		return  ResponseEntity.ok(this.categoryService.getCategoryById(categoryId)); 
	}
     
}
