package com.example.capstone02.Service;

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

//    public Boolean addFeedback(Feedback feedback) {
//        User user = userRepository.findUserById(feedback.getUser_id());
//        Event event = eventRepository.findEventById(feedback.getEvent_id());
//
//        if (user == null || event == null) {
//            return false;
//        }
//
//        feedback.setSubmitted_at(LocalDate.now());
//        feedbackRepository.save(feedback);
//        return true;
//    }

    public Boolean updateFeedback(Integer id, Feedback updatedFeedback) {
        Feedback oldFeedback = feedbackRepository.findFeedbackById(id);
        if (oldFeedback == null) {
            return false;
        }

        User user = userRepository.findUserById(updatedFeedback.getUser_id());
        Event event = eventRepository.findEventById(updatedFeedback.getEvent_id());

        if (user == null || event == null) {
            return false;
        }

        oldFeedback.setUser_id(updatedFeedback.getUser_id());
        oldFeedback.setEvent_id(updatedFeedback.getEvent_id());
        oldFeedback.setComment(updatedFeedback.getComment());
        oldFeedback.setRating(updatedFeedback.getRating());

        feedbackRepository.save(oldFeedback);
        return true;
    }

    public Boolean deleteFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findFeedbackById(id);
        if (feedback == null) {
            return false;
        }

        feedbackRepository.delete(feedback);
        return true;
    }

    //8. submit feedback of visited events
    public Boolean submitFeedback(Feedback feedback) {
        User user = userRepository.findUserById(feedback.getUser_id());
        Event event = eventRepository.findEventById(feedback.getEvent_id());

        if (user == null || event == null) {
            return false;
        }

        // Check if event has already occurred
        if (event.getDate_time().isAfter(LocalDate.now())) {
            return false;
        }

        // Check for duplicate feedback
        Feedback existing = feedbackRepository.findFeedbackByUserAndEvent(feedback.getUser_id(), feedback.getEvent_id());
        if (existing != null) {
            return false;
        }

        feedback.setSubmitted_at(LocalDate.now());
        feedbackRepository.save(feedback);
        return true;
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
