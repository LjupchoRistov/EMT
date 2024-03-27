package com.emt.service.impl;

import com.emt.model.Country;
import com.emt.model.Host;
import com.emt.model.dto.HostDto;
import com.emt.model.exception.CountryNotFoundException;
import com.emt.model.exception.HostNotFoundException;
import com.emt.model.mapper.HostMapper;
import com.emt.repository.CountryRepository;
import com.emt.repository.HostRepository;
import com.emt.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<HostDto> listAll() {
        return this.hostRepository.findAll().stream().map(HostMapper::hostDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Optional<HostDto> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Optional<Host> host = this.hostRepository.findById(id);

        if (host.isEmpty())
            throw new HostNotFoundException(id);

        return Optional.of(hostDtoMapper(host.get()));
    }

    @Override
    public List<HostDto> findAllByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        List<Host> hosts = this.hostRepository.findAllByNameEquals(name);

        if (hosts.isEmpty())
            throw new HostNotFoundException(name);

        return hosts.stream().map(HostMapper::hostDtoMapper).toList();
    }

    @Override
    public List<HostDto> findAllBySurname(String surname) {
        if (surname == null || surname.isEmpty())
            throw new IllegalArgumentException();

        List<Host> hosts = this.hostRepository.findAllBySurnameEquals(surname);

        if (hosts.isEmpty())
            throw new HostNotFoundException(surname);

        return hosts.stream().map(HostMapper::hostDtoMapper).toList();
    }

    @Override
    public List<HostDto> findAllByNameAndSurname(String name, String surname) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty())
            throw new IllegalArgumentException();

        List<Host> hosts = this.hostRepository.findAllByNameEqualsAndSurnameEquals(name, surname);

        if (hosts.isEmpty())
            throw new HostNotFoundException(name, surname);

        return hosts.stream().map(HostMapper::hostDtoMapper).toList();
    }

    @Override
    public Optional<HostDto> create(HostDto hostDto) {
        Optional<Country> country = Optional.ofNullable(this.countryRepository.findByName(hostDto.getCountryName()).orElseThrow(() -> new CountryNotFoundException(hostDto.getCountryName())));

        if (country.isEmpty())
            throw new CountryNotFoundException(hostDto.getCountryName());

        Host host = new Host(hostDto.getName(), hostDto.getSurname(), country.get());

        this.hostRepository.save(host);

        return Optional.of(hostDtoMapper(host));
    }

    @Override
    public Optional<HostDto> edit(Long id, HostDto hostDto) {
        if (id == null)
            throw new IllegalArgumentException();

        Host host = this.hostRepository.findById(id).orElseThrow(() -> new HostNotFoundException(id));

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(hostDto.getCountry());

        this.hostRepository.save(host);

        return Optional.of(hostDtoMapper(host));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException();

        Host host = this.hostRepository.findById(id).orElseThrow(() -> new HostNotFoundException(id));

        this.hostRepository.delete(host);
    }
}
