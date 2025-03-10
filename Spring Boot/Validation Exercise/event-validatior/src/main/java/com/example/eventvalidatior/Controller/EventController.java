package com.example.eventvalidatior.Controller;

import com.example.eventvalidatior.Api.ApiResponse;
import com.example.eventvalidatior.Model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event added successfully"));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        events.set(index, event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event updated successfully"));
    }
    @DeleteMapping("/delete/{index}")
    @JsonFormat
    public ResponseEntity deleteEvent(@PathVariable int index) {
        if (index < 0 || index >= events.size())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds"));
        events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event deleted successfully"));
    }
    @PutMapping("/updatecapacity/{index}/{capacity}")
    public ResponseEntity changeEventCapacity(@PathVariable int index, @PathVariable int capacity) {
        if (index < 0 || index >= events.size() || capacity <= 25)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds or Capacity is less than 26"));
        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event capacity updated successfully"));
    }
    @GetMapping("/search/{id}")
    public Object searchEvents(@PathVariable String id) {
        for (Event event : events) {
            if (event.getId().equalsIgnoreCase(id)) {
                return event;
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Event not found"));
    }
}
