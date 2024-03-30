package com.emt.service.impl;

import com.emt.model.Accommodation;
import com.emt.model.Host;
import com.emt.model.dto.AccommodationDto;
import com.emt.model.exception.AccommodationNotFoundException;
import com.emt.model.exception.HostNotFoundException;
import com.emt.model.exception.NotEnoughRoomsAccommodationException;
import com.emt.repository.AccommodationRepository;
import com.emt.repository.HostRepository;
import com.emt.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.emt.model.mapper.AccommodationMapper.*;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> listAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Optional<Accommodation> accommodation = this.accommodationRepository.findById(id);
        if (accommodation.isEmpty())
            throw new AccommodationNotFoundException(id);

        return accommodation;
    }

    @Override
    public Optional<Accommodation> findByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        Optional<Accommodation> accommodation = this.accommodationRepository.findByName(name);
        if (accommodation.isEmpty())
            throw new AccommodationNotFoundException(name);

        return accommodation;
    }

    @Override
    public Optional<Accommodation> create(AccommodationDto accommodationDto) {
        Host host = this.hostRepository.findById(accommodationDto.getHostId()).orElseThrow(() -> new HostNotFoundException(accommodationDto.getHostId()));
        Accommodation accommodation = new Accommodation(accommodationDto.getName(), accommodationDto.getNumRooms(), accommodationDto.getCategory(), host);

        return Optional.of(this.accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto) {
        if (id == null)
            throw new IllegalArgumentException();

        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        accommodation.setName(accommodationDto.getName());
        Host host = this.hostRepository.findById(accommodationDto.getHostId()).orElseThrow(() -> new HostNotFoundException(accommodationDto.getHostId()));
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setCategory(accommodationDto.getCategory());

        this.accommodationRepository.save(accommodation);

        return Optional.of(accommodation);
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

        return accommodation.getNumRooms() == 0;
    }

    @Override
    public void book(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        int freeRooms = accommodation.getNumRooms();

        if (freeRooms <= 0)
            throw new NotEnoughRoomsAccommodationException(accommodation.getName(), id);

        accommodation.setNumRooms(freeRooms - 1);

        this.accommodationRepository.save(accommodation);
    }

    @Override
    public void removeBook(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new AccommodationNotFoundException(id));

        accommodation.setNumRooms(accommodation.getNumRooms() + 1);

        this.accommodationRepository.save(accommodation);
    }
}
