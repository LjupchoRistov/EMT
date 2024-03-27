package com.emt.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccommodationNotFoundException extends RuntimeException{
    public AccommodationNotFoundException(Long id) {
        super(String.format("Accommodation with id: %d is not found", id));
    }
    public AccommodationNotFoundException(String name) {
        super(String.format("Accommodation with id: %s is not found", name));
    }
}
