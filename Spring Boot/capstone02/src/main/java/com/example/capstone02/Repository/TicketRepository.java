package com.example.capstone02.Repository;

import com.example.capstone02.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findTicketById(Integer id);

    @Query("select t from Ticket t where t.user_id=?1")
    List<Ticket> findAllTicketsByUserId(Integer userId);

    @Query("select t from Ticket t where t.booking_id=?1")
    Ticket getTicketByBookingId(Integer bookingId);

}
