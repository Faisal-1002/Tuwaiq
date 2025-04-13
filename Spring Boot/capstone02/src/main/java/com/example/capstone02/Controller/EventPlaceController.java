package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.EventPlace;
import com.example.capstone02.Service.EventPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/place")
@RequiredArgsConstructor
public class EventPlaceController {

    private final EventPlaceService eventPlaceService;

    @GetMapping("/get")
    public ResponseEntity<List<EventPlace>> getAllPlaces() {
        return ResponseEntity.ok(eventPlaceService.getAllPlaces());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPlace(@Valid @RequestBody EventPlace place, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = eventPlaceService.addPlace(place);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Place added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add place"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updatePlace(@PathVariable Integer id,
                                                   @Valid @RequestBody EventPlace place,
                                                   Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = eventPlaceService.updatePlace(id, place);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Place updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Place not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePlace(@PathVariable Integer id) {
        Boolean success = eventPlaceService.deletePlace(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Place deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Place not found"));
        }
    }
}