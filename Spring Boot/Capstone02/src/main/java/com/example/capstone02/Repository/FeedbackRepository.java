package com.example.capstone02.Repository;

import com.example.capstone02.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    Feedback findFeedbackById(Integer id);

    @Query("SELECT f FROM Feedback f WHERE f.user_id = ?1 AND f.event_id = ?2")
    Feedback findFeedbackByUserAndEvent(Integer userId, Integer eventId);

    @Query("SELECT f FROM Feedback f WHERE f.event_id = ?1")
    List<Feedback> findAllByEventId(Integer eventId);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.event_id = ?1")
    Double getAverageRatingByEventId(Integer eventId);

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.event_id = ?1")
    Integer getFeedbackCountByEventId(Integer eventId);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.event_id IN ?1")
    Double avgRatingByEventIds(List<Integer> eventIds);



}
