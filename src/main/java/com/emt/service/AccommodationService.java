package com.emt.service;

import com.emt.model.Accommodation;
import com.emt.model.dto.AccommodationDto;
import io.micrometer.observation.ObservationFilter;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> findByName(String name);
    Optional<Accommodation> create(AccommodationDto accommodationDto);
    Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto);
    void delete(Long id);
    boolean isBooked(Long id);
    void book(Long id);
    void removeBook(Long id);
}
