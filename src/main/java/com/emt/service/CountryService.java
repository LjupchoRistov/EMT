package com.emt.service;

import com.emt.model.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> findByName(String name);
    Optional<Country> create(Country countryDto);
    Optional<Country> edit(Long id, Country countryDto);
    void delete(Long id);
}
