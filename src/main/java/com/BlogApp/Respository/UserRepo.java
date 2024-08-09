package com.BlogApp.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entites.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
	Optional<User>findByEmail(String email);	
}
