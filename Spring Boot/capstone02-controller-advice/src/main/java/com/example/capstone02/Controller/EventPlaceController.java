package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.EventPlace;
import com.example.capstone02.Service.EventPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/place")
@RequiredArgsConstructor
public class EventPlaceController {

    private final EventPlaceService eventPlaceService;

    @GetMapping("/get")
    public ResponseEntity getAllPlaces() {
        return ResponseEntity.status(200).body(eventPlaceService.getAllPlaces());
    }

    @PostMapping("/add")
    public ResponseEntity addPlace(@Valid @RequestBody EventPlace place, Errors errors) {
       eventPlaceService.addPlace(place);
       return ResponseEntity.status(200).body(new ApiResponse("Place added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePlace(@PathVariable Integer id, @Valid @RequestBody EventPlace place, Errors errors) {
        eventPlaceService.updatePlace(id, place);
        return ResponseEntity.status(200).body(new ApiResponse("Place updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlace(@PathVariable Integer id) {
       eventPlaceService.deletePlace(id);
       return ResponseEntity.status(200).body(new ApiResponse("Place deleted successfully"));
    }
}