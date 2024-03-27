package com.emt.web.rest;

import com.emt.model.dto.AccommodationDto;
import com.emt.model.dto.HostDto;
import com.emt.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostRestController {
    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    // List all (WORKING)
    @GetMapping()
    public List<HostDto> findAll(){
        return this.hostService.listAll();
    }

    // Find with id (WORKING)
    @GetMapping("/{id}")
    public ResponseEntity<HostDto> findById(@PathVariable Long id){
        return this.hostService.findById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Add (WORKING)
    @PostMapping("/add")
    public ResponseEntity<HostDto> create(@RequestBody HostDto hostDto){
        return this.hostService.create(hostDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Edit (WORKING)
    @PostMapping("/{id}/edit")
    public ResponseEntity<HostDto> edit(@PathVariable Long id, @RequestBody HostDto hostDto){
        return this.hostService.edit(id, hostDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HostDto> delete(@PathVariable Long id) {
        this.hostService.delete(id);

        if (this.hostService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok().build();
    }
}
