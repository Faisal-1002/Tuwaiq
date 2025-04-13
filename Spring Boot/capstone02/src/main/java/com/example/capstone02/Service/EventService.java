package com.example.capstone02.Service;

import com.example.capstone02.Model.Event;
import com.example.capstone02.Model.User;
import com.example.capstone02.Model.EventPlace;
import com.example.capstone02.Repository.BookingRepository;
import com.example.capstone02.Repository.EventRepository;
import com.example.capstone02.Repository.UserRepository;
import com.example.capstone02.Repository.EventPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final BookingRepository bookingRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Boolean addEvent(Event event) {
        User user = userRepository.findUserById(event.getUser_id());
        EventPlace place = eventPlaceRepository.findEventPlaceById(event.getPlace_id());

        if (user == null || place == null || !user.getRole().equalsIgnoreCase("host")) {
            return false;
        }

        Integer totalCapacity = eventRepository.getTotalCapacityByPlaceId(place.getId());

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
        Integer totalCapacity = eventRepository.getTotalCapacityByPlaceId(place.getId()) - oldEvent.getMax_capacity();

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

    public List<Event> getEventsBySearchFilter(String category, String title) {
//        return eventRepository.searchAndFilterEvents(keyword, category, date);
        return eventRepository.findEventByCategoryContainingOrTitleContaining(category, title);
    }

    public List<Event> suggestEventsForUser(Integer userId) {
        List<Integer> eventIds = bookingRepository.findDistinctEventIdsByUserId(userId);
        if (eventIds.isEmpty())
            return null;

        List<String> categories = eventRepository.findCategoriesByEventIds(eventIds);
        if (categories.isEmpty())
            return null;

        return eventRepository.findUpcomingEventsByCategories(categories);
    }


}
