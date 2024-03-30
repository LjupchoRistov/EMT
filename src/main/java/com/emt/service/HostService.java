package com.emt.service;

import com.emt.model.Host;
import com.emt.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> listAll();
    Optional<Host> findById(Long id);
    List<Host> findAllByName(String name);
    List<Host> findAllBySurname(String surname);
    List<Host> findAllByNameAndSurname(String name, String surname);
    Optional<Host> create(HostDto hostDto);
    Optional<Host> edit(Long id, HostDto hostDto);
    void delete(Long id);
}
