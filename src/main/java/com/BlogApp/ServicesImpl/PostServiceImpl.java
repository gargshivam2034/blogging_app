package com.BlogApp.ServicesImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.BlogApp.Payloads.PostDto;
import com.BlogApp.Payloads.PostResponse;
import com.BlogApp.Respository.CategoryRepo;
import com.BlogApp.Respository.PostRepo;
import com.BlogApp.Respository.UserRepo;
import com.BlogApp.Services.PostService;
import com.BlogApp.entites.Category;
import com.BlogApp.entites.Post;
import com.BlogApp.entites.User;
import com.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private  PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
    @Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer categoryId,Integer userId) {
		
		Post post=modelMapper.map(postDto, Post.class);
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Id",categoryId));
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
	    post.setUser(user);
	    post.setCategory(category);
	    post.setAddedDate(new Date());
	    post.setImageName("default.png");
	    return modelMapper.map(this.postRepo.save(post),PostDto.class);
	}

	@Override
	public PostDto updatePost( PostDto postDto, Integer id) {
	  Post post= postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id", id));	
	  post.setContent(postDto.getContent());
	  post.setTitle(postDto.getTitle());
	  post.setImageName(postDto.getImageName());
	  Post updatePost=this.postRepo.save(post);
	  return modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		this.postRepo.deleteById(id);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
	Pageable pageable=PageRequest.of(pageNumber, pageSize);
	PostResponse postResponse=new PostResponse();
	Page<Post>pagePost =this.postRepo.findAll(pageable);
	List<Post>  allPosts=pagePost.getContent();
	List<PostDto>postDtos=allPosts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	postResponse.setContent(postDtos);
	postResponse.setPageSize(pageSize);
	postResponse.setPageNumber(pageNumber);
	postResponse.setTotalPages(pagePost.getTotalPages());
	postResponse.setTotalElements(pagePost.getTotalElements());
	postResponse.setLastPage(pagePost.isLast());
	return postResponse;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id));
		PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("CategoryId","Id",categoryId));
        List<Post>posts=this.postRepo.findByCategory(category);		
		List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
 		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("UserId","Id", userId));
        List<Post>posts=this.postRepo.findByUser(user);
 		List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;

	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post>posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto>dtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public long getPostCount() {
	  long noOfTotalPosts= this.postRepo.count();
		return noOfTotalPosts;
	}
	
	
	

}
