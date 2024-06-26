package com.emt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String continent;

    // Relations
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    //private List<Host> hosts;


    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}