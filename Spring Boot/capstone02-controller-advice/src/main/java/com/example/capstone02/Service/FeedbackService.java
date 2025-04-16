package com.example.capstone02.Service;

import com.example.capstone02.Api.ApiException;
import com.example.capstone02.Model.Feedback;
import com.example.capstone02.Model.User;
import com.example.capstone02.Model.Event;
import com.example.capstone02.Repository.FeedbackRepository;
import com.example.capstone02.Repository.UserRepository;
import com.example.capstone02.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public void updateFeedback(Integer id, Feedback updatedFeedback) {
        Feedback oldFeedback = feedbackRepository.findFeedbackById(id);
        if (oldFeedback == null) {
            throw new ApiException("Feedback not found");
        }

        User user = userRepository.findUserById(updatedFeedback.getUser_id());
        Event event = eventRepository.findEventById(updatedFeedback.getEvent_id());

        if (user == null || event == null) {
            throw new ApiException("User not found");
        }

        oldFeedback.setUser_id(updatedFeedback.getUser_id());
        oldFeedback.setEvent_id(updatedFeedback.getEvent_id());
        oldFeedback.setComment(updatedFeedback.getComment());
        oldFeedback.setRating(updatedFeedback.getRating());

        feedbackRepository.save(oldFeedback);
    }

    public void deleteFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findFeedbackById(id);
        if (feedback == null) {
            throw new ApiException("Feedback not found");
        }
        feedbackRepository.delete(feedback);
    }

    //8. submit feedback of visited events
    public void submitFeedback(Feedback feedback) {
        User user = userRepository.findUserById(feedback.getUser_id());
        Event event = eventRepository.findEventById(feedback.getEvent_id());
        if (user == null || event == null) {
            throw new ApiException("User not found");
        }
        // Check if event has already occurred
        if (event.getDate_time().isAfter(LocalDate.now())) {
            throw new ApiException("Date time is after current date");
        }
        // Check for duplicate feedback
        Feedback existing = feedbackRepository.findFeedbackByUserAndEvent(feedback.getUser_id(), feedback.getEvent_id());
        if (existing != null) {
            throw new ApiException("Feedback already exists");
        }
        feedback.setSubmitted_at(LocalDate.now());
        feedbackRepository.save(feedback);
    }

    //9. Feedback for an event
    public List<Feedback> getFeedbackForEvent(Integer eventId) {
        return feedbackRepository.findAllByEventId(eventId);
    }

    //10 Feedback stats
    public Map<String, Object> getFeedbackSummaryForEvent(Integer eventId) {
        Double average = feedbackRepository.getAverageRatingByEventId(eventId);
        Integer count = feedbackRepository.getFeedbackCountByEventId(eventId);

        Map<String, Object> summary = new HashMap<>();
        summary.put("averageRating", average != null ? average : 0.0);
        summary.put("feedbackCount", count != null ? count : 0);

        return summary;
    }
}
