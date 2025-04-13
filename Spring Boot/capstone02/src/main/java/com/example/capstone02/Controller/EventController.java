package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Event;
import com.example.capstone02.Service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/get")
    public ResponseEntity getAllEvents() {
        return ResponseEntity.status(200).body(eventService.getAllEvents());
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        Boolean success = eventService.addEvent(event);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Failed to add event"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Boolean success = eventService.updateEvent(id, event);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id) {
        Boolean success = eventService.deleteEvent(id);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
    }

    @GetMapping("/search-filter/{category}/{title}")
    public ResponseEntity getEventsBySearchFilter(@PathVariable String category, @PathVariable String title){
        return ResponseEntity.status(200).body(eventService.getEventsBySearchFilter(category,title));
    }

    @GetMapping("/suggest/{userId}")
    public ResponseEntity suggestEventsForUser(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(eventService.suggestEventsForUser(userId));
    }

    @GetMapping("/host/{hostId}/top-booked")
    public ResponseEntity getTopBookedEvents(@PathVariable Integer hostId) {
        return ResponseEntity.status(200).body(eventService.getTopThreeBookedEventsByHost(hostId));
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity getEventsByHost(@PathVariable Integer hostId) {
        return ResponseEntity.status(200).body(eventService.getAllEventsByHost(hostId));
    }

    @GetMapping("/host/{hostId}/insights")
    public ResponseEntity getHostInsights(@PathVariable Integer hostId) {
        return ResponseEntity.status(200).body(eventService.getHostPerformanceSummary(hostId));
    }

}
