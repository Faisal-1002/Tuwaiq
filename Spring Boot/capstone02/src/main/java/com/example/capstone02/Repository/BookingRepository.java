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
    @Query("SELECT DISTINCT b.event_id FROM Booking b WHERE b.user_id = ?1")
    List<Integer> findDistinctEventIdsByUserId(Integer userId);
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.user_id = ?1")
    Integer countBookingsByUserId(Integer userId);
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.user_id = ?1 AND b.event_id = ?2")
    Integer countBookingsByUserIdAndEventId(Integer userId, Integer eventId);
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.event_id = ?1")
    Integer countBookingsByEventId(Integer eventId);



}
