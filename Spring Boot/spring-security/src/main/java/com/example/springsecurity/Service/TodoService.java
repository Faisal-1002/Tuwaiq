package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import com.example.springsecurity.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

    public Set<Todo> getMyTodo(Integer user_id){
        User user = authRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("User not found");
        return user.getTodos();
    }

    public List<Todo> getAllTodos(Integer user_id){
        User user = authRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("User not found");
        return todoRepository.findAll();
    }

    public void addTodos(Integer user_id, Todo todo){
        User user = authRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("User not found");
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void updateTodos(Integer user_id, Integer todo_id, Todo todo){
        User user = authRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("User not found");
        Todo updatedTodo = todoRepository.findTodoById(todo_id);
        if (updatedTodo == null)
            throw new ApiException("Todo not found");
        if (updatedTodo.getUser() != user)
            throw new ApiException("Todo does not belong to the user");
        updatedTodo.setTitle(todo.getTitle());
        updatedTodo.setStatus(todo.getStatus());
        todoRepository.save(todo);
    }

    public void deleteTodo(Integer user_id, Integer todo_id){
        User user = authRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("User not found");
        Todo ToBeDeletedTodo = todoRepository.findTodoById(todo_id);
        if (ToBeDeletedTodo == null)
            throw new ApiException("Todo not found");
        todoRepository.delete(ToBeDeletedTodo);
    }

}
