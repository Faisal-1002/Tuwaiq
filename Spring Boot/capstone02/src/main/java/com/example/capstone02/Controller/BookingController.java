package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Booking;
import com.example.capstone02.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/get")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBooking(@Valid @RequestBody Booking booking, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = bookingService.addBooking(booking);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Booking added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add booking"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateBooking(@PathVariable Integer id, @Valid @RequestBody Booking booking, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = bookingService.updateBooking(id, booking);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Booking updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Booking not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable Integer id) {
        Boolean success = bookingService.cancelBooking(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Booking deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Booking not found"));
        }
    }

    @PostMapping("/book/{userId}/{eventId}/{groupSize}")
    public ResponseEntity<ApiResponse> bookEvent(@PathVariable Integer userId, @PathVariable Integer eventId, @PathVariable Integer groupSize) {
        Boolean success = bookingService.bookEvent(userId, eventId, groupSize);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Event booked successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Booking failed: check eligibility, capacity, or conflicts"));
        }
    }

}
