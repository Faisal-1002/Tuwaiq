package com.example.springsecurity.Controller;

import com.example.springsecurity.Api.ApiResponse;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity<?> getMyTodos(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(todoService.getMyTodo(user.getId()));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTodos(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(todoService.getAllTodos(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMyTodo(@AuthenticationPrincipal User user, @RequestBody @Valid Todo todo){
        todoService.addTodos(user.getId(), todo);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }

    @PutMapping("/update/{todo_id}")
    public ResponseEntity<?> updateMyTodo(@AuthenticationPrincipal User user, @PathVariable Integer todo_id, @RequestBody @Valid Todo todo){
        todoService.updateTodos(user.getId(), todo_id, todo);
        return ResponseEntity.status(200).body(new ApiResponse("Updated"));
    }

    @DeleteMapping("/delete/{todo_id}")
    public ResponseEntity<?> deleteMyTodo(@AuthenticationPrincipal User user, @PathVariable Integer todo_id){
        todoService.deleteTodo(user.getId(), todo_id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }

}
