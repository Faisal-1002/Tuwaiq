package com.example.lab_11.Controller;

import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.Comment;
import com.example.lab_11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (commentService.addComment(comment))
            return ResponseEntity.status(200).body(new ApiResponse("Added"));
        return ResponseEntity.status(400).body(new ApiResponse("not added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @Valid @RequestBody Comment comment, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (commentService.updateComment(id, comment))
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        if (commentService.deleteComment(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not deleted"));
    }

    @GetMapping("/get-by-user-id/{id}")
    public ResponseEntity getCommentsByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getCommentsByUserId(id));
    }

    @GetMapping("/get-by-post-id/{id}")
    public ResponseEntity getCommentsByPostId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getCommentsByPostId(id));
    }

}
