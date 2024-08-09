package com.BlogApp.Respository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
