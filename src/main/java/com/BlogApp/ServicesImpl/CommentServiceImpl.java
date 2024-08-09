package com.BlogApp.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Observable.NotifyMeOnComment;
import com.BlogApp.Observer.Observer;
import com.BlogApp.Payloads.CommentDto;
import com.BlogApp.Respository.CommentRepo;
import com.BlogApp.Respository.PostRepo;
import com.BlogApp.Services.CommentService;
import com.BlogApp.entites.Comment;
import com.BlogApp.entites.Post;
import com.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements CommentService,NotifyMeOnComment{
	
	@Autowired
	private Observer observer;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("PostId","Id", postId));
		Comment comment =modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment createdComment=this.commentRepo.save(comment);
		this.notifyMe(post.getUser().getName());
		return modelMapper.map(createdComment,CommentDto.class);
	}

	@Override
	public CommentDto updatedComment(Integer commnetId, CommentDto commentDto) { 
		return null;
	}

	@Override
	public void deleteComment(Integer commentId) {
		this.commentRepo.deleteById(commentId);
	}

	@Override
	public List<CommentDto> getAllComments() {
		List<Comment>comments=this.commentRepo.findAll();
		return comments.stream().map((comment)->modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("CommentId","Id",commentId));
		return modelMapper.map(comment,CommentDto.class);
	}

	@Override
	public void add(Observer observer) {
		
	}

	@Override
	public void remove(Observer observer) {
		
	}

	@Override
	public void notifyMe(String name) {
		observer.update(name);
		
	}
	
	

}
