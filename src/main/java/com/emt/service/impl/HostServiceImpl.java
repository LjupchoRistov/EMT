package com.emt.service.impl;

import com.emt.model.Country;
import com.emt.model.Host;
import com.emt.model.dto.HostDto;
import com.emt.model.exception.CountryNotFoundException;
import com.emt.model.exception.HostNotFoundException;
import com.emt.repository.CountryRepository;
import com.emt.repository.HostRepository;
import com.emt.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.emt.model.mapper.HostMapper.*;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> listAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Optional<Host> host = this.hostRepository.findById(id);

        if (host.isEmpty())
            throw new HostNotFoundException(id);

        return host;
    }

    @Override
    public List<Host> findAllByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        List<Host> hosts = this.hostRepository.findAllByNameEquals(name);

        if (hosts.isEmpty())
            throw new HostNotFoundException(name);

        return hosts;
    }

    @Override
    public List<Host> findAllBySurname(String surname) {
        if (surname == null || surname.isEmpty())
            throw new IllegalArgumentException();

        List<Host> hosts = this.hostRepository.findAllBySurnameEquals(surname);

        if (hosts.isEmpty())
            throw new HostNotFoundException(surname);

        return hosts;
    }

    @Override
    public List<Host> findAllByNameAndSurname(String name, String surname) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty())
            throw new IllegalArgumentException();

        List<Host> hosts = this.hostRepository.findAllByNameEqualsAndSurnameEquals(name, surname);

        if (hosts.isEmpty())
            throw new HostNotFoundException(name, surname);

        return hosts;
    }

    @Override
    public Optional<Host> create(HostDto hostDto) {
        Optional<Country> country = this.countryRepository.findById(hostDto.getCountryId());

        if (country.isEmpty())
            throw new CountryNotFoundException(hostDto.getCountryId());

        Host host = new Host(hostDto.getName(), hostDto.getSurname(), country.get());

        this.hostRepository.save(host);

        return Optional.of(host);
    }

    @Override
    public Optional<Host> edit(Long id, HostDto hostDto) {
        if (id == null)
            throw new IllegalArgumentException();

        Host host = this.hostRepository.findById(id).orElseThrow(() -> new HostNotFoundException(id));

        Optional<Country> country = this.countryRepository.findById(hostDto.getCountryId());

        if (country.isEmpty())
            throw new CountryNotFoundException(hostDto.getCountryId());

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(country.get());

        this.hostRepository.save(host);

        return Optional.of(host);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Host host = this.hostRepository.findById(id).orElseThrow(() -> new HostNotFoundException(id));

        this.hostRepository.delete(host);
    }
}
