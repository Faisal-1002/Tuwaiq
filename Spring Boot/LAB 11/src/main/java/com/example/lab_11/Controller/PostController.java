package com.example.lab_11.Controller;

import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPosts(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (postService.addPost(post))
            return ResponseEntity.status(200).body(new ApiResponse("Added"));
        return ResponseEntity.status(400).body(new ApiResponse("not added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @Valid @RequestBody Post post, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (postService.updatePost(id, post))
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        if (postService.deletePost(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not deleted"));
    }

    @GetMapping("/get-by-user-id/{id}")
    public ResponseEntity getAllPostsByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.getAllPostsByUserId(id));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getAllPostsByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.getAllPostsByTitle(title));
    }

    @GetMapping("/get-before-date/{date}")
    public ResponseEntity getAllPostsBeforeADate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(postService.getAllPostsBeforeADate(date));
    }

    @GetMapping("/get-by-category/{id}")
    public ResponseEntity getAllPostsByCategory(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.getAllPostsByCategoryId(id));
    }
}
