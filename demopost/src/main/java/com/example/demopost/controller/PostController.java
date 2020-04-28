package com.example.demopost.controller;

import com.example.demopost.exception.ResourceNotFoundException;
import com.example.demopost.model.Post;
import com.example.demopost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public Page<Post> getAllPost(Pageable pageable) {
        return postService.findAll(pageable);
    }

    //Page: class, ir a la siguiente/anterior página, recuperar la infórmación.

    //Pageable: interface que tiene número de páginas, formato

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postService.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postService.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Post Id " + postId + " no found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postService.findById(postId).map(post -> {
            postService.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Post Id " + postId + " no found"));
    }
}
