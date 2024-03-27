package com.emt.repository;

import com.emt.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<Host> findAllByNameEqualsAndSurnameEquals(String name, String surname);
    List<Host> findAllByNameEquals(String name);
    List<Host> findAllBySurnameEquals(String surname);
}
