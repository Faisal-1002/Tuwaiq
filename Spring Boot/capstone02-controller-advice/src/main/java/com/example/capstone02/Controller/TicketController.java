package com.example.capstone02.Controller;

import com.example.capstone02.Api.ApiResponse;
import com.example.capstone02.Model.Ticket;
import com.example.capstone02.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/get")
    public ResponseEntity getAllTickets() {
        return ResponseEntity.status(200).body(ticketService.getAllTickets());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTicket(@PathVariable Integer id, @Valid @RequestBody Ticket ticket) {
        ticketService.updateTicket(id, ticket);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket deleted successfully"));
    }

    @PostMapping("/generate")
    public ResponseEntity generateTicket(@Valid @RequestBody Ticket ticket) {
        ticketService.generateTicketWithQRCode(ticket);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket generated successfully"));
    }

    @PostMapping("/send/{ticketId}")
    public ResponseEntity sendNotification(@PathVariable Integer ticketId) {
       ticketService.sendNotificationTo(ticketId);
       return ResponseEntity.status(200).body(new ApiResponse("Ticket sent to user's email successfully"));
    }

    @PostMapping("/scan/{ticketId}")
    public ResponseEntity scanAndValidateTicket(@PathVariable Integer ticketId) {
        ticketService.scanAndValidateTicket(ticketId);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket is valid. Access granted."));
    }

    @GetMapping("/get-tickets-by-userid/{userId}")
    public ResponseEntity getAllTicketsForUser(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(ticketService.getAllTicketsForUser(userId));
    }
}
