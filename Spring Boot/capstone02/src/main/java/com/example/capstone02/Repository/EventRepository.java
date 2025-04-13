package com.example.capstone02.Repository;

import com.example.capstone02.Model.Event;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findEventById(Integer id);
    @Query("SELECT COALESCE(SUM(e.max_capacity), 0) FROM Event e WHERE e.place_id=?1")
    Integer getTotalCapacityByPlaceId(Integer placeId);

    @Query("""
        SELECT e FROM Event e WHERE (:keyword IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
              AND (:category IS NULL OR e.category = :category)
              AND (:date IS NULL OR e.date_time = :date)
    """)
    List<Event> searchAndFilterEvents(String keyword, String category, LocalDate date);

    List<Event> findEventByCategoryContainingOrTitleContaining(String category, String title);
    @Query("SELECT DISTINCT e.category FROM Event e WHERE e.id IN ?1")
    List<String> findCategoriesByEventIds(List<Integer> eventIds);

    @Query("SELECT e FROM Event e WHERE e.category IN ?1 AND e.date_time > CURRENT_DATE")
    List<Event> findUpcomingEventsByCategories(List<String> categories);


}
