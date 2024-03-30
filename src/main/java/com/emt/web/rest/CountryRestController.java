package com.emt.web.rest;

import com.emt.model.Country;
import com.emt.model.dto.CountryDto;
import com.emt.model.dto.HostDto;
import com.emt.service.CountryService;
import com.emt.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    // List all (WORKING)
    @GetMapping()
    public List<Country> findAll(){
        return this.countryService.listAll();
    }

    // Find with id (WORKING)
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Add (WORKING)
    @PostMapping("/add")
    public ResponseEntity<Country> create(@RequestBody CountryDto countryDto){
        return this.countryService.create(countryDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Edit (WORKING)
    @PostMapping("/{id}/edit")
    public ResponseEntity<Country> edit(@PathVariable Long id, @RequestBody CountryDto countryDto){
        return this.countryService.edit(id, countryDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Delete (WORKING)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id) {
        this.countryService.delete(id);

        if (this.countryService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok().build();
    }
}
