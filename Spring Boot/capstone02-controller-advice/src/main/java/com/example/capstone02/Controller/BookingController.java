package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Booking;
import com.example.capstone02.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/get")
    public ResponseEntity getAllBookings() {
        return ResponseEntity.status(200).body(bookingService.getAllBookings());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBooking(@PathVariable Integer id, @Valid @RequestBody Booking booking) {
        bookingService.updateBooking(id, booking);
        return ResponseEntity.status(200).body(new ApiResponse("Booking updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity cancelBooking(@PathVariable Integer id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.status(200).body(new ApiResponse("Booking deleted successfully"));
    }

    @PostMapping("/book/{userId}/{eventId}/{groupSize}")
    public ResponseEntity bookEvent(@PathVariable Integer userId, @PathVariable Integer eventId, @PathVariable Integer groupSize) {
        bookingService.bookEvent(userId, eventId, groupSize);
        return ResponseEntity.status(200).body(new ApiResponse("Event booked successfully"));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity getBookingsByEvent(@PathVariable Integer eventId) {
        return ResponseEntity.status(200).body(bookingService.getAllBookingsForEvent(eventId));
    }

}
