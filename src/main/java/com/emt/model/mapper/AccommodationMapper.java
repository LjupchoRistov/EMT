package com.emt.model.mapper;

import com.emt.model.Accommodation;
import com.emt.model.Host;
import com.emt.model.dto.AccommodationDto;
import com.emt.model.exception.HostNotFoundException;
import com.emt.repository.HostRepository;

public class AccommodationMapper {
    public static Accommodation accommodationMapper (AccommodationDto accommodationDto, HostRepository hostRepository){
        Host host = hostRepository.findById(accommodationDto.getHost()).orElseThrow(() -> new HostNotFoundException(accommodationDto.getHost()));

        return Accommodation.builder()
                .id(accommodationDto.getId())
                .name(accommodationDto.getName())
                .category(accommodationDto.getCategory())
                .numRooms(accommodationDto.getNumRooms())
                .isBooked(accommodationDto.getIsBooked())
                .host(host)
                .build();
    }

    public static AccommodationDto accommodationDtoMapper (Accommodation accommodation){
        return AccommodationDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .category(accommodation.getCategory())
                .numRooms(accommodation.getNumRooms())
                .isBooked(accommodation.getIsBooked())
                .host(accommodation.getHost().getId())
                .build();
    }
}
