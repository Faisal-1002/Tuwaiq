package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.Api.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("Task has been added successfully");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("Task has been updated successfully");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("Task has been deleted successfully");
    }
    @PutMapping("/updateTaskStatus/{index}/{status}")
    public ApiResponse updateTasksStatus(@PathVariable int index, @PathVariable String status) {
        tasks.get(index).setStatus(status);
        return new ApiResponse("Task status has been updated successfully");
    }
    @GetMapping("/searchTasks/{title}")
    public Object searchTasks(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title))
                return task;
        }
        return new ApiResponse("Task not found");
    }
}
