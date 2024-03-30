package com.emt.service;

import com.emt.model.Country;
import com.emt.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> findByName(String name);
    Optional<Country> create(CountryDto countryDto);
    Optional<Country> edit(Long id, CountryDto countryDto);
    void delete(Long id);
}
