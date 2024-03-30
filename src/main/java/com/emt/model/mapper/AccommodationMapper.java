package com.emt.model.mapper;

import com.emt.model.Accommodation;
import com.emt.model.Host;
import com.emt.model.dto.AccommodationDto;
import com.emt.model.exception.HostNotFoundException;
import com.emt.repository.HostRepository;

public class AccommodationMapper {
    public static AccommodationDto accommodationDtoMapper (Accommodation accommodation){
        return AccommodationDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .category(accommodation.getCategory())
                .numRooms(accommodation.getNumRooms())
                .hostId(accommodation.getHost().getId())
                .build();
    }
}
