package com.BlogApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.CommentDto;
import com.BlogApp.Services.CommentService;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	public  ResponseEntity<CommentDto> createComment
	(@RequestBody CommentDto commentDto,@PathVariable Integer postId) {
	  CommentDto createdCommentDto=this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createdCommentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public void deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		
	}
	
	@GetMapping("/comments")
	@Timed(value = "get.comments.duration", description = "Time taken to fetch all comments")
	@Counted(value = "api.comments.hits",description = "no of times api is hit")
	public ResponseEntity<List<CommentDto>> getAllComments() {
	List<CommentDto> commentDtos=this.commentService.getAllComments();
	return  new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId) {
		CommentDto commentDto=this.commentService.getCommentById(commentId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
	}

}
