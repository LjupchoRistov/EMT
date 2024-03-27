package com.emt.model;

import com.emt.model.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private Integer numRooms;
    private Boolean isBooked;

    public Accommodation(String name, Integer numRooms, Category category, Host host) {
        this.name = name;
        this.numRooms = numRooms;
        this.category = category;
        this.host = host;
        this.isBooked = false;
    }

    // Relations
    @ManyToOne
    private Host host;
}