package com.emt.service;

import com.emt.model.dto.AccommodationDto;
import io.micrometer.observation.ObservationFilter;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<AccommodationDto> listAll();
    Optional<AccommodationDto> findById(Long id);
    Optional<AccommodationDto> findByName(String name);
    Optional<AccommodationDto> create(AccommodationDto accommodationDto);
    Optional<AccommodationDto> edit(Long id, AccommodationDto accommodationDto);
    void delete(Long id);
    boolean isBooked(Long id);
    void book(Long id);
    void removeBook(Long id);
}
