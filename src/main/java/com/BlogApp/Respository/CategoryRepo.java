package com.BlogApp.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BlogApp.entites.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
