package com.emt.web.rest;

import com.emt.model.Accommodation;
import com.emt.model.dto.AccommodationDto;
import com.emt.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class AccommodationRestController {
    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    // List all (WORKING)
    @GetMapping
    public List<Accommodation> findAll(){
        return this.accommodationService.listAll();
    }

    // Find with id (WORKING)
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id){
        return this.accommodationService.findById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Add (WORKING)
    @PostMapping("/add")
    public ResponseEntity<Accommodation> create(@RequestBody AccommodationDto accommodationDto){
        return this.accommodationService.create(accommodationDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Edit (WORKING)
    @PostMapping("/{id}/edit")
    public ResponseEntity<Accommodation> edit(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto){
        return this.accommodationService.edit(id, accommodationDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Set accommodation as booked (WORKING)
    @PostMapping("/book/{id}")
    public ResponseEntity<AccommodationDto> book(@PathVariable Long id){
         this.accommodationService.book(id);

        if (this.accommodationService.isBooked(id))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    // Set accommodation as free for book (WORKING)
    @PostMapping("/book/{id}/remove")
    public ResponseEntity<AccommodationDto> removeBook(@PathVariable Long id){
        if (!this.accommodationService.isBooked(id))
            return ResponseEntity.badRequest().build();

        this.accommodationService.removeBook(id);

        if (!this.accommodationService.isBooked(id))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();

    }

    // Delete (WORKING)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccommodationDto> delete(@PathVariable Long id) {
        this.accommodationService.delete(id);

        if (this.accommodationService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        else return ResponseEntity.status(123).build();
    }
}
