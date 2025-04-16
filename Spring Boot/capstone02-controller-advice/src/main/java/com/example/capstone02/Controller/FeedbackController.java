package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Feedback;
import com.example.capstone02.Service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("/get")
    public ResponseEntity getAllFeedback() {
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateFeedback(@PathVariable Integer id, @Valid @RequestBody Feedback feedback) {
        feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback deleted successfully"));
    }

    @PostMapping("/submit")
    public ResponseEntity submitFeedback(@Valid @RequestBody Feedback feedback) {
        feedbackService.submitFeedback(feedback);
        return ResponseEntity.status(200).body(new ApiResponse("Feedback submitted successfully"));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity getFeedbackForEvent(@PathVariable Integer eventId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackForEvent(eventId));
    }

    @GetMapping("/event/{eventId}/summary")
    public ResponseEntity getFeedbackSummary(@PathVariable Integer eventId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackSummaryForEvent(eventId));
    }
}