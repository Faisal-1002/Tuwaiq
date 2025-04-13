package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Notification;
import com.example.capstone02.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/get")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNotification(@Valid @RequestBody Notification notification, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = notificationService.addNotification(notification);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Notification added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add notification"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateNotification(@PathVariable Integer id,
                                                          @Valid @RequestBody Notification notification,
                                                          Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = notificationService.updateNotification(id, notification);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Notification updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Notification not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteNotification(@PathVariable Integer id) {
        Boolean success = notificationService.deleteNotification(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Notification deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Notification not found"));
        }
    }
}
