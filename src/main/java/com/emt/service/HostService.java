package com.emt.service;

import com.emt.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<HostDto> listAll();
    Optional<HostDto> findById(Long id);
    List<HostDto> findAllByName(String name);
    List<HostDto> findAllBySurname(String surname);
    List<HostDto> findAllByNameAndSurname(String name, String surname);
    Optional<HostDto> create(HostDto hostDto);
    Optional<HostDto> edit(Long id, HostDto hostDto);
    void delete(Long id);
}
