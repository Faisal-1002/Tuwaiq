package com.example.capstone02.Service;

import com.example.capstone02.Api.ApiException;
import com.example.capstone02.Model.EventPlace;
import com.example.capstone02.Repository.EventPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventPlaceService {

    private final EventPlaceRepository eventPlaceRepository;

    public List<EventPlace> getAllPlaces() {
        return eventPlaceRepository.findAll();
    }

    public void addPlace(EventPlace place) {
        eventPlaceRepository.save(place);
    }

    public void updatePlace(Integer id, EventPlace updatedPlace) {
        EventPlace oldPlace = eventPlaceRepository.findEventPlaceById(id);
        if (oldPlace == null)
            throw new ApiException("Place not found");
        oldPlace.setName(updatedPlace.getName());
        oldPlace.setLocation(updatedPlace.getLocation());
        oldPlace.setCapacity(updatedPlace.getCapacity());
        eventPlaceRepository.save(oldPlace);
    }

    public void deletePlace(Integer id) {
        EventPlace place = eventPlaceRepository.findEventPlaceById(id);
        if (place == null)
            throw new ApiException("Place not found");
        eventPlaceRepository.delete(place);
    }
}
