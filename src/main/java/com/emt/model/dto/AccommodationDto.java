package com.emt.model.dto;

import com.emt.model.Host;
import com.emt.model.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccommodationDto {
    private Long id;

    private String name;
    private Category category;
    private Integer numRooms;

    private Long hostId;
}