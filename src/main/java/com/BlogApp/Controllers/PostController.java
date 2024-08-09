package com.BlogApp.Controllers;
import java.io.InputStream;
import java.util.List;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BlogApp.Payloads.PostDto;
import com.BlogApp.Payloads.PostResponse;
import com.BlogApp.Services.FileService;
import com.BlogApp.Services.PostService;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired	
	private ObservationRegistry observationRegistry;
	
	 @Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer categoryId, 
			@PathVariable Integer userId ) {
		
		PostDto createdPostDto =this.postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<PostDto>(createdPostDto,HttpStatus.CREATED);	
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
		PostDto updatedpostDto=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedpostDto,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{postId}") 
	public void deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
	}
	
	@GetMapping("/posts")
	public PostResponse getAllPosts(@RequestParam(value="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "3",required = false) Integer pageSize) {
		
	return this.postService.getAllPost(pageNumber,pageSize); 	
	}
	
	@GetMapping("/{postId}")
	public PostDto getPostById(@PathVariable Integer postId)
	{
		return this.postService.getPostById(postId);
	}
	
	@GetMapping("/user/{userId}/posts")
	public  ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postDtos=this.postService.getPostByUser(userId);
		return Observation.createNotStarted("/user/{userId}/posts", observationRegistry).observe(
				()->new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK));
	}
	@GetMapping("/category/{categoryId}/posts")
	public  ResponseEntity<List<PostDto>> getPostBycategory(@PathVariable Integer categoryId) {
		List<PostDto> postDtos=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/title/{title}/posts")
	public ResponseEntity<List<PostDto>>getPostsByTitle(@PathVariable String title)
	{
		List<PostDto>postsDtos=this.postService.searchPosts(title);
		return new ResponseEntity<List<PostDto>>(postsDtos,HttpStatus.OK);
	}
	
	@PutMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws Exception
	{
		PostDto  postDto=this.postService.getPostById(postId);
		String imageName=this.fileService.uploadImage(image, path);
		postDto.setImageName(imageName);
		PostDto updatedPostDto=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	
		
	}
	@GetMapping(value="post/image/{imageName}",  produces  =  MediaType.IMAGE_JPEG_VALUE)
	public void  DownloadFile(@PathVariable("imageName") String imageName, HttpServletResponse httpServletResponse) throws Exception
	{
	    InputStream inputStream=fileService.ServeFile( imageName,path);
	    httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    StreamUtils.copy(inputStream,httpServletResponse.getOutputStream());

	}
	
	@GetMapping("/postCount")
	public ResponseEntity<?> getPostCount()
	{
		long postCount=this.postService.getPostCount();
		return new ResponseEntity<Long>(postCount,HttpStatus.OK);
	}
	
	

}
