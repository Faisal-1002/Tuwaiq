package com.example.lab05eventsystem.Controller;

import com.example.lab05eventsystem.Api.ApiResponse;
import com.example.lab05eventsystem.Model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Event event) {
        events.set(index, event);
        return new ApiResponse("Event updated successfully");
    }
    @DeleteMapping("/delete/{index}")
    @JsonFormat
    public ApiResponse deleteEvent(@PathVariable int index) {
        events.remove(index);
        return new ApiResponse("Event deleted successfully");
    }
    @PutMapping("/updatecapacity/{index}/{capacity}")
    public ApiResponse changeEventCapacity(@PathVariable int index, @PathVariable int capacity) {
        events.get(index).setCapacity(capacity);
        return new ApiResponse("Event capacity updated successfully");
    }
    @GetMapping("/search/{id}")
    public Object searchEvents(@PathVariable String id) {
        for (Event event : events) {
            if (event.getId().equalsIgnoreCase(id)) {
                return event;
            }
        }
        return new ApiResponse("Event not found");
    }
}
