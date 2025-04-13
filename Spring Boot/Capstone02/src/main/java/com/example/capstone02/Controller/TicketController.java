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

//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addTicket(@Valid @RequestBody Ticket ticket, Errors errors) {
//        if (errors.hasErrors()) {
//            return ResponseEntity.status(400)
//                    .body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
//        }
//
//        Boolean success = ticketService.addTicket(ticket);
//        if (success) {
//            return ResponseEntity.status(200).body(new ApiResponse("Ticket added successfully"));
//        } else {
//            return ResponseEntity.status(400).body(new ApiResponse("Failed to add ticket"));
//        }
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTicket(@PathVariable Integer id, @Valid @RequestBody Ticket ticket, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Boolean success = ticketService.updateTicket(id, ticket);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Ticket updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Ticket not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTicket(@PathVariable Integer id) {
        Boolean success = ticketService.deleteTicket(id);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Ticket deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Ticket not found"));
    }

    @PostMapping("/generate")
    public ResponseEntity generateTicket(@Valid @RequestBody Ticket ticket, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        Boolean success = ticketService.generateTicketWithQRCode(ticket);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Ticket generated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Ticket generation failed: invalid or duplicate booking"));
    }

    @PostMapping("/send/{ticketId}")
    public ResponseEntity sendNotification(@PathVariable Integer ticketId) {
        Boolean success = ticketService.sendNotificationTo(ticketId);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Ticket sent to user's email successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Failed to send ticket: ticket or user not found"));
    }

    @PostMapping("/scan/{ticketId}")
    public ResponseEntity scanAndValidateTicket(@PathVariable Integer ticketId) {
        Boolean success = ticketService.scanAndValidateTicket(ticketId);
        if (success)
            return ResponseEntity.status(200).body(new ApiResponse("Ticket is valid. Access granted."));
        return ResponseEntity.status(400).body(new ApiResponse("Invalid or already used ticket."));
    }

    @GetMapping("/get-tickets-by-userid/{userId}")
    public ResponseEntity getAllTicketsForUser(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(ticketService.getAllTicketsForUser(userId));
    }
}
