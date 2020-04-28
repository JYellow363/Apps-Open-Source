package com.example.demopost.controller;

import com.example.demopost.exception.ResourceNotFoundException;
import com.example.demopost.model.Comment;
import com.example.demopost.service.CommentService;
import com.example.demopost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{postId}/{comments}")
    //Obtener los comentarios filtrados por id

    public Page<Comment> getAllCommentsById(@PathVariable Long postId, Pageable pageable) {
        return commentService.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable Long postId, @Valid @RequestBody Comment comment) {
        return postService.findById(postId).map(post -> {
            comment.setPost(post);
            return commentService.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        // variante del create
        if (!postService.existsById(postId)) {
            throw new ResourceNotFoundException("Post Id " + postId + " not found");
        }

        return commentService.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentService.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {

        return commentService.findByIdAndPostId(commentId, postId).map(comment -> {

            commentService.delete(comment);
            return ResponseEntity.ok().build();

        }).orElseThrow(() -> new ResourceNotFoundException("CommentId not found with id " + commentId + " and postId " + postId));
    }
}
