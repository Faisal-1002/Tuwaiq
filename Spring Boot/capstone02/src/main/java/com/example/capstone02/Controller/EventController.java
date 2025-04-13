package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Event;
import com.example.capstone02.Service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/get")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = eventService.addEvent(event);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add event"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateEvent(@PathVariable Integer id, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = eventService.updateEvent(id, event);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable Integer id) {
        Boolean success = eventService.deleteEvent(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
        }
    }

    @GetMapping("/search-filter/{category}/{title}")
    public ResponseEntity<List<Event>> getEventsBySearchFilter(@PathVariable String category, @PathVariable String title){
        return ResponseEntity.ok(eventService.getEventsBySearchFilter(category,title));
    }

    @GetMapping("/suggest/{userId}")
    public ResponseEntity<List<Event>> suggestEventsForUser(@PathVariable Integer userId) {
        List<Event> suggested = eventService.suggestEventsForUser(userId);
        return ResponseEntity.ok(suggested);
    }


}
