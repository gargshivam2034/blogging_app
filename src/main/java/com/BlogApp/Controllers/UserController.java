package com.BlogApp.Controllers;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.service.annotation.PutExchange;

import com.BlogApp.Payloads.UserApiResponse;
import com.BlogApp.Payloads.UserDto;
//import com.BlogApp.Respository.UserRepo;
import com.BlogApp.Services.UserService;

import jakarta.validation.Valid;
//import com.BlogApp.entites.User;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto  createuserDto= this.userService.createUser(userDto);
		return new ResponseEntity<>(createuserDto,HttpStatus.CREATED);
	}
	@PutMapping("/{userId}")
	public  ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId)
	{
		UserDto updatedUser=this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserApiResponse> deleteUser(@PathVariable Integer userId )
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity(new UserApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return  ResponseEntity.ok(this.userService.getAllUsers()); 
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return  ResponseEntity.ok(this.userService.getUserById(userId)); 
	}
		
}
