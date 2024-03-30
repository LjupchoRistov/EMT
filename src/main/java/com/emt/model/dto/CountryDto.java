package com.emt.model.dto;

import com.emt.model.Host;
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
public class CountryDto {
    private Long id;

    private String name;
    private String continent;
}