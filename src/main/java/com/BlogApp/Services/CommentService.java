package com.BlogApp.Services;

import java.util.List;

import com.BlogApp.Payloads.CommentDto;

public interface CommentService {
	
	CommentDto  createComment(CommentDto commentDto,Integer postId);
	CommentDto  updatedComment(Integer commnetId,CommentDto commentDto);
	void deleteComment(Integer commentId);
	List<CommentDto> getAllComments();
	CommentDto  getCommentById(Integer commentId);
	

}
