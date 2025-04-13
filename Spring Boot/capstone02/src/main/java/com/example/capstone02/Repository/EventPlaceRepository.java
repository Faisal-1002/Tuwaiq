package com.example.capstone02.Repository;

import com.example.capstone02.Model.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Integer> {

    EventPlace findEventPlaceById(Integer id);

}
