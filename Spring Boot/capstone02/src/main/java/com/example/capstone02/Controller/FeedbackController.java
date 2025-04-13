package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Feedback;
import com.example.capstone02.Service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("/get")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFeedback(@Valid @RequestBody Feedback feedback, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = feedbackService.addFeedback(feedback);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Feedback added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add feedback"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateFeedback(@PathVariable Integer id, @Valid @RequestBody Feedback feedback, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = feedbackService.updateFeedback(id, feedback);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Feedback updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Feedback not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFeedback(@PathVariable Integer id) {
        Boolean success = feedbackService.deleteFeedback(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Feedback deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Feedback not found"));
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse> submitFeedback(@Valid @RequestBody Feedback feedback, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = feedbackService.submitFeedback(feedback);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Feedback submitted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Feedback submission failed: event not finished or already submitted"));
        }
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Feedback>> getFeedbackForEvent(@PathVariable Integer eventId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackForEvent(eventId));
    }

    @GetMapping("/event/{eventId}/summary")
    public ResponseEntity<Map<String, Object>> getFeedbackSummary(@PathVariable Integer eventId) {
        return ResponseEntity.status(200).body(feedbackService.getFeedbackSummaryForEvent(eventId));
    }


}