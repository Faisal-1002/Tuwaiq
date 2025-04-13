package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Ticket;
import com.example.capstone02.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/get")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTicket(@Valid @RequestBody Ticket ticket, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = ticketService.addTicket(ticket);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Ticket added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add ticket"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateTicket(@PathVariable Integer id, @Valid @RequestBody Ticket ticket, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        Boolean success = ticketService.updateTicket(id, ticket);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Ticket updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Ticket not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTicket(@PathVariable Integer id) {
        Boolean success = ticketService.deleteTicket(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Ticket deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Ticket not found"));
        }
    }

    @PostMapping("/generate/{id}")
    public ResponseEntity<ApiResponse> generateTicket(@PathVariable Integer id) {
        Boolean success = ticketService.generateTicket(id);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Ticket generated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Ticket generation failed: invalid or duplicate booking"));
        }
    }

    @PostMapping("/send/{ticketId}")
    public ResponseEntity<ApiResponse> sendNotification(@PathVariable Integer ticketId) {
        Boolean success = ticketService.sendNotificationTo(ticketId);

        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Ticket sent to user's email successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to send ticket: ticket or user not found"));
        }
    }

    @PostMapping("/scan/{ticketId}")
    public ResponseEntity<ApiResponse> scanAndValidateTicket(@PathVariable Integer ticketId) {
        Boolean success = ticketService.scanAndValidateTicket(ticketId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Ticket is valid. Access granted."));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid or already used ticket."));
        }
    }

    @GetMapping("/get-tickets-by-userid/{userId}")
    public ResponseEntity<List<Ticket>> getAllTicketsForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(ticketService.getAllTicketsForUser(userId));
    }


}
