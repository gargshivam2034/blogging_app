package com.BlogApp.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Payloads.UserDto; 
import com.BlogApp.Respository.UserRepo;
import com.BlogApp.Services.UserService;
import com.BlogApp.entites.User;
import com.BlogApp.exceptions.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
        User user =this.dtoToUser(userDto);
		userRepo.save(user);
		return this.userToDtoUser(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		user.setId(id);
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		return userToDtoUser(updatedUser);
		
	}

	@Override
	public UserDto getUserById(Integer id) {
		 User user= this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id", id));
		 return this.userToDtoUser(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
	  List<User>list =userRepo.findAll();
	  return list.stream().map(user->this.userToDtoUser(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer id) {
	    User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id", id));
		this.userRepo.delete(user);
	}
	public User dtoToUser(UserDto userDto)
	{
	   User user=modelMapper.map(userDto, User.class);
//	   user.setId(userDto.getId());
//	   user.setEmail(userDto.getEmail());
//	   user.setName(userDto.getName());
//	   user.setPassword(userDto.getPassword());
//	   user.setAbout(userDto.getAbout());
	   return user;
	}
	public UserDto userToDtoUser(User user)
	{
	   
	   UserDto userDto=modelMapper.map(user, UserDto.class);
//	   UserDto userDto=new UserDto();
//	   userDto.setId(user.getId());
//	   userDto.setEmail(user.getEmail());
//	   userDto.setName(user.getName());
//	   userDto.setPassword(user.getPassword());
//	   userDto.setAbout(user.getAbout());
	   return userDto;
	}
	

}
