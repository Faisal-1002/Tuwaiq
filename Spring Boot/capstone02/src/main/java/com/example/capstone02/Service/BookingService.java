package com.example.capstone02.Service;

import com.example.capstone02.Model.Booking;
import com.example.capstone02.Model.EventPlace;
import com.example.capstone02.Model.User;
import com.example.capstone02.Model.Event;
import com.example.capstone02.Repository.BookingRepository;
import com.example.capstone02.Repository.EventPlaceRepository;
import com.example.capstone02.Repository.UserRepository;
import com.example.capstone02.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventPlaceRepository eventPlaceRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

//    public Boolean addBooking(Booking booking) {
//        User user = userRepository.findUserById(booking.getUser_id());
//        Event event = eventRepository.findEventById(booking.getEvent_id());
//
//        if (user == null || event == null) {
//            return false;
//        }
//
//        booking.setBooking_time(LocalDate.now());
//        bookingRepository.save(booking);
//        return true;
//    }

    public Boolean updateBooking(Integer id, Booking updatedBooking) {
        Booking oldBooking = bookingRepository.findBookingById(id);
        User user = userRepository.findUserById(updatedBooking.getUser_id());
        Event event = eventRepository.findEventById(updatedBooking.getEvent_id());
        if (oldBooking == null || user == null || event == null) {
            return false;
        }
        oldBooking.setUser_id(updatedBooking.getUser_id());
        oldBooking.setEvent_id(updatedBooking.getEvent_id());
        oldBooking.setStatus(updatedBooking.getStatus());
        oldBooking.setTotal_price(updatedBooking.getTotal_price());
        bookingRepository.save(oldBooking);
        return true;
    }

    public Boolean cancelBooking(Integer id) {
        Booking booking = bookingRepository.findBookingById(id);
        if (booking == null)
            return false;
        Event event = eventRepository.findEventById(booking.getEvent_id());
        if (event == null) {
            return false;
        }
        booking.setStatus("cancelled");
        bookingRepository.save(booking);
        event.setBooking_count(event.getBooking_count() - 1);
        eventRepository.save(event);
        return true;
    }

    //4. Book event (constrains : group size 1 - 5, Event capacity, Conflict time events)
    public Boolean bookEvent(Integer userId, Integer eventId, Integer groupSize) {
        if (groupSize < 1 || groupSize > 5)
            return false;

        User user = userRepository.findUserById(userId);
        if (user == null)
            return false;
        Event event = eventRepository.findEventById(eventId);
        if (event == null)
            return false;
        EventPlace place = eventPlaceRepository.findEventPlaceById(event.getPlace_id());
        if (place == null)
            return false;

        // Check total event capacity
        Integer bookedCount = bookingRepository.countBookingsByEventId(eventId);
        if (bookedCount + groupSize > event.getMax_capacity())
            return false;

        // Check for conflicting event time
        for (Booking b : bookingRepository.findAll()) {
            if (b.getUser_id().equals(userId)) {
                Event e = eventRepository.findEventById(b.getEvent_id());
                if (e != null && e.getDate_time().equals(event.getDate_time())) {
                    return false;
                }
            }
        }

        // Discount Logic
        double discount = 0.0;
        if ("premium".equalsIgnoreCase(user.getMembership())) {
            discount = 0.25;
        } else if (bookingRepository.countBookingsByUserId(userId) == 0) {
            discount = 0.20;
        }

        if (groupSize >= 3) {
            discount = Math.max(discount, 0.15);
        }

        double basePrice = event.getPrice();
        double finalPrice = basePrice * (1 - discount);

        // Save each booking
        for (int i = 0; i < groupSize; i++) {
            Booking booking = new Booking();
            booking.setUser_id(userId);
            booking.setEvent_id(eventId);
            booking.setStatus("confirmed");
            booking.setBooking_time(LocalDate.now());
            booking.setTotal_price(finalPrice);
            bookingRepository.save(booking);
        }

        event.setBooking_count(event.getBooking_count() + groupSize);
        eventRepository.save(event);

        return true;
    }

    //16. Get All bookings for an event
    public List<Booking> getAllBookingsForEvent(Integer eventId) {
        return bookingRepository.findAllByEventId(eventId);
    }
}
