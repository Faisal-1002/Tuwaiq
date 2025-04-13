package com.example.capstone02.Service;

import com.example.capstone02.Model.Event;
import com.example.capstone02.Model.User;
import com.example.capstone02.Repository.EventRepository;
import com.example.capstone02.Repository.UserRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import com.example.capstone02.Model.Ticket;
import com.example.capstone02.Model.Booking;
import com.example.capstone02.Repository.TicketRepository;
import com.example.capstone02.Repository.BookingRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

//    public Boolean addTicket(Ticket ticket) {
//        Booking booking = bookingRepository.findBookingById(ticket.getBooking_id());
//        if (booking == null) {
//            return false;
//        }
//
//        ticket.setIssued_at(LocalDate.now());
//        ticketRepository.save(ticket);
//        return true;
//    }

    public Boolean updateTicket(Integer id, Ticket updatedTicket) {
        Ticket oldTicket = ticketRepository.findById(id).orElse(null);
        if (oldTicket == null) {
            return false;
        }
        Booking booking = bookingRepository.findBookingById(updatedTicket.getBooking_id());
        if (booking == null) {
            return false;
        }
        oldTicket.setBooking_id(updatedTicket.getBooking_id());
        ticketRepository.save(oldTicket);
        return true;
    }

    public Boolean deleteTicket(Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) {
            return false;
        }
        ticketRepository.delete(ticket);
        return true;
    }
    //5. Add and generate ticket qr code
    public Boolean generateTicketWithQRCode(Ticket ticket) {
        Booking booking = bookingRepository.findBookingById(ticket.getBooking_id());
        if (booking == null)
            return false;

        // Check if a ticket already exists for this booking
        Ticket existing = ticketRepository.getTicketByBookingId(ticket.getBooking_id());
        if (existing != null)
            return false;

        // Set ticket data
        ticket.setUser_id(booking.getUser_id());
        ticket.setIssued_at(LocalDate.now());
        ticket.setStatus(true);

        // Save initially to get the ID (for QR generation)
        Ticket savedTicket = ticketRepository.save(ticket);

        try {
            String qrText = String.valueOf(savedTicket.getId());

            BitMatrix matrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, 250, 250);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", stream);

            String base64Qr = Base64.getEncoder().encodeToString(stream.toByteArray());
            savedTicket.setQr_image(base64Qr);

            ticketRepository.save(savedTicket); // save again after QR image

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    //6. Send ticket through email
    public Boolean sendNotificationTo(Integer ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        if (ticket == null) return false;

        Booking booking = bookingRepository.findBookingById(ticket.getBooking_id());
        if (booking == null) return false;

        User user = userRepository.findUserById(booking.getUser_id());
        if (user == null) return false;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(user.getEmail());
            helper.setSubject("Your Event Ticket");
            helper.setText("Hi " + user.getName() + ",\n\nHere is your ticket QR code. Please bring it with you!");

            // Decode Base64 and attach QR image
            byte[] qrBytes = Base64.getDecoder().decode(ticket.getQr_image());
            helper.addAttachment("ticket-qr.png", new ByteArrayResource(qrBytes));

            mailSender.send(message);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //7. Scan ticket and make its status expired
    public Boolean scanAndValidateTicket(Integer ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        Booking booking = bookingRepository.findBookingById(ticket.getBooking_id());
        Event event = eventRepository.findEventById(booking.getEvent_id());

        if (ticket == null || ticket.getStatus() == null || !ticket.getStatus()) {
            return false;
        }
        if (!event.getDate_time().equals(LocalDate.now())) {
            return false;
        }

        ticket.setStatus(false);
        ticketRepository.save(ticket);
        return true;
    }

    //15. Get tickets by user id
    public List<Ticket> getAllTicketsForUser(Integer userId) {
        return ticketRepository.findAllTicketsByUserId(userId);
    }
}
