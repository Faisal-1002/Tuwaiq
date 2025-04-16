package com.example.capstone02.Repository;

import com.example.capstone02.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findBookingById(Integer id);

    @Query("SELECT b FROM Booking b WHERE b.event_id = ?1")
    List<Booking> findAllByEventId(Integer eventId);

    @Query("SELECT DISTINCT b.event_id FROM Booking b WHERE b.user_id = ?1")
    List<Integer> findDistinctEventIdsByUserId(Integer userId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.user_id = ?1")
    Integer countBookingsByUserId(Integer userId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.event_id = ?1")
    Integer countBookingsByEventId(Integer eventId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.event_id IN ?1")
    Integer countBookingsByEventIds(List<Integer> eventIds);

    @Query("SELECT SUM(b.total_price) FROM Booking b WHERE b.event_id IN ?1")
    Double sumRevenueByEventIds(List<Integer> eventIds);



}
