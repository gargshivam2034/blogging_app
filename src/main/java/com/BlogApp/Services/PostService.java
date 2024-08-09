package com.BlogApp.Services;

import java.util.List;

import com.BlogApp.Payloads.CategoryDto;
import com.BlogApp.Payloads.PostDto;
import com.BlogApp.Payloads.PostResponse;
import com.BlogApp.Payloads.UserDto;
import com.BlogApp.entites.Category;

public interface PostService {
	
	public PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
	public PostDto updatePost(PostDto postDto,Integer id);
	public void deletePost(Integer id);
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	public PostDto getPostById(Integer id);
	public List<PostDto> getPostByCategory(Integer categoryId);
	public List<PostDto> getPostByUser(Integer userId);
	public List<PostDto> searchPosts(String keyword);
	public long getPostCount();
}
