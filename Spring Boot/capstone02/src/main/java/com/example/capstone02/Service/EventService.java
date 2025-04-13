package com.example.capstone02.Service;

import com.example.capstone02.Model.Event;
import com.example.capstone02.Model.User;
import com.example.capstone02.Model.EventPlace;
import com.example.capstone02.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final BookingRepository bookingRepository;
    private final FeedbackRepository feedbackRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    //1. Add event based on EventPlace capacity
    public Boolean addEvent(Event event) {
        User user = userRepository.findUserById(event.getUser_id());
        EventPlace place = eventPlaceRepository.findEventPlaceById(event.getPlace_id());

        if (user == null || place == null || !user.getRole().equalsIgnoreCase("host")) {
            return false;
        }

        Integer totalCapacity = eventRepository.getTotalCapacityByPlaceIdAndDateTime(place.getId(), event.getDate_time());

        if (totalCapacity + event.getMax_capacity() > place.getCapacity()) {
            return false;
        }

        eventRepository.save(event);
        return true;
    }

    public Boolean updateEvent(Integer id, Event updatedEvent) {
        Event oldEvent = eventRepository.findEventById(id);
        if (oldEvent == null) {
            return false;
        }

        User user = userRepository.findUserById(updatedEvent.getUser_id());
        EventPlace place = eventPlaceRepository.findEventPlaceById(updatedEvent.getPlace_id());

        if (user == null || place == null || !user.getRole().equalsIgnoreCase("host")) {
            return false;
        }

        // Get total capacity at the place and subtract the old event's current capacity
        Integer totalCapacity = eventRepository.getTotalCapacityByPlaceIdAndDateTime(place.getId(), oldEvent.getDate_time()) - oldEvent.getMax_capacity();

        if (totalCapacity + updatedEvent.getMax_capacity() > place.getCapacity()) {
            return false;
        }

        oldEvent.setTitle(updatedEvent.getTitle());
        oldEvent.setDescription(updatedEvent.getDescription());
        oldEvent.setCategory(updatedEvent.getCategory());
        oldEvent.setDate_time(updatedEvent.getDate_time());
        oldEvent.setUser_id(updatedEvent.getUser_id());
        oldEvent.setPlace_id(updatedEvent.getPlace_id());
        oldEvent.setMax_capacity(updatedEvent.getMax_capacity());
        oldEvent.setPrice(updatedEvent.getPrice());

        eventRepository.save(oldEvent);
        return true;
    }

    public Boolean deleteEvent(Integer id) {
        Event event = eventRepository.findEventById(id);
        if (event == null) {
            return false;
        }

        eventRepository.delete(event);
        return true;
    }

    //3. Search and filter events
    public List<Event> getEventsBySearchFilter(String category, String title) {
        return eventRepository.findEventByCategoryContainingOrTitleContaining(category, title);
    }

    //11. Suggests events for user based on booked events
    public List<Event> suggestEventsForUser(Integer userId) {
        List<Integer> eventIds = bookingRepository.findDistinctEventIdsByUserId(userId);
        if (eventIds.isEmpty())
            return null;

        List<String> categories = eventRepository.findCategoriesByEventIds(eventIds);
        if (categories.isEmpty())
            return null;

        return eventRepository.findUpcomingEventsByCategories(categories);
    }

    //12. Top 3 booked events
    public List<Event> getTopThreeBookedEventsByHost(Integer hostId) {
        List<Event> events = eventRepository.findTopBookedEventsByHost(hostId);
        return events.size() > 3 ? events.subList(0, 3) : events;
    }

    //13. Get event by host id
    public List<Event> getAllEventsByHost(Integer hostId) {
        return eventRepository.findAllEventsByHostId(hostId);
    }

    //14. Host statics
    public Map<String, Object> getHostPerformanceSummary(Integer hostId) {
        List<Integer> eventIds = eventRepository.findEventIdsByHost(hostId);
        if (eventIds.isEmpty()) return Map.of(
                "totalBookings", 0,
                "totalRevenue", 0.0,
                "averageRating", 0.0
        );

        Integer totalBookings = bookingRepository.countBookingsByEventIds(eventIds);
        Double totalRevenue = bookingRepository.sumRevenueByEventIds(eventIds);
        Double averageRating = feedbackRepository.avgRatingByEventIds(eventIds);

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalBookings", totalBookings);
        summary.put("totalRevenue", totalRevenue != null ? totalRevenue : 0.0);
        summary.put("averageRating", averageRating != null ? averageRating : 0.0);

        return summary;
    }

}
