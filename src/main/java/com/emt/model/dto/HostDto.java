package com.emt.model.dto;

import com.emt.model.Accommodation;
import com.emt.model.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HostDto {
    private Long id;

    private String name;
    private String surname;

    private Long countryId;
}