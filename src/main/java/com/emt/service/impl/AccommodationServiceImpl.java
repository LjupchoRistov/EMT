package com.emt.service.impl;

import com.emt.model.Accommodation;
import com.emt.model.Host;
import com.emt.model.dto.AccommodationDto;
import com.emt.model.exception.AccommodationNotFoundException;
import com.emt.model.exception.HostNotFoundException;
import com.emt.model.mapper.AccommodationMapper;
import com.emt.repository.AccommodationRepository;
import com.emt.repository.HostRepository;
import com.emt.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.emt.model.mapper.AccommodationMapper.*;
import static com.emt.model.mapper.HostMapper.*;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<AccommodationDto> listAll() {
        return this.accommodationRepository.findAll().stream().map(AccommodationMapper::accommodationDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Optional<AccommodationDto> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Optional<Accommodation> accommodation = this.accommodationRepository.findById(id);
        if (accommodation.isEmpty())
            throw new AccommodationNotFoundException(id);


        return Optional.of(accommodationDtoMapper(accommodation.get()));
    }

    @Override
    public Optional<AccommodationDto> findByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        Optional<Accommodation> accommodation = this.accommodationRepository.findByName(name);
        if (accommodation.isEmpty())
            throw new AccommodationNotFoundException(name);

        return Optional.of(accommodationDtoMapper(accommodation.get()));
    }

    @Override
    public Optional<AccommodationDto> create(AccommodationDto accommodationDto) {
        // Accommodation accommodation = accommodationMapper(accommodationDto);

        Host host = this.hostRepository.findById(accommodationDto.getHost()).orElseThrow(() -> new HostNotFoundException(accommodationDto.getHost()));
        Accommodation accommodation = new Accommodation(accommodationDto.getName(), accommodationDto.getNumRooms(), accommodationDto.getCategory(), host);

        this.accommodationRepository.save(accommodation);

        return Optional.of(accommodationDtoMapper(accommodation));
    }

    @Override
    public Optional<AccommodationDto> edit(Long id, AccommodationDto accommodationDto) {
        if (id == null)
            throw new IllegalArgumentException();

        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        accommodation.setName(accommodationDto.getName());
        Host host = this.hostRepository.findById(accommodationDto.getHost()).orElseThrow(() -> new HostNotFoundException(accommodationDto.getHost()));
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setIsBooked(accommodationDto.getIsBooked());

        this.accommodationRepository.save(accommodation);

        return Optional.of(accommodationDtoMapper(accommodation));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        this.accommodationRepository.delete(accommodation);
    }

    @Override
    public boolean isBooked(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        return accommodation.getIsBooked();
    }

    @Override
    public void book(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        accommodation.setIsBooked(true);
        this.accommodationRepository.save(accommodation);
    }

    @Override
    public void removeBook(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        accommodation.setIsBooked(false);
        this.accommodationRepository.save(accommodation);
    }

}
