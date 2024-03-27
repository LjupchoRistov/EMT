package com.emt.service.impl;

import com.emt.model.Country;
import com.emt.model.exception.CountryNotFoundException;
import com.emt.repository.CountryRepository;
import com.emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return new ArrayList<>(this.countryRepository.findAll());
    }

    @Override
    public Optional<Country> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Optional<Country> country = this.countryRepository.findById(id);

        if (country.isEmpty())
            throw new CountryNotFoundException(id);

        return country;
    }

    @Override
    public Optional<Country> findByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        Optional<Country> country = this.countryRepository.findByName(name);

        if (country.isEmpty())
            throw new CountryNotFoundException(name);

        return country;
    }

    @Override
    public Optional<Country> create(Country country) {
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, Country oldVersion) {
        if (id == null)
            throw new IllegalArgumentException();

        Country newVersion = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        newVersion.setName(oldVersion.getName());

        this.countryRepository.save(newVersion);

        return Optional.of(newVersion);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        this.countryRepository.delete(country);
    }
}
