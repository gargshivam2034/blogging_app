package com.BlogApp.Respository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entites.Post;
import java.util.List;
import com.BlogApp.entites.User;
import com.BlogApp.entites.Category;



public interface PostRepo extends JpaRepository<Post,Integer> {
	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	public List<Post> findByTitleContaining(String title);

}
