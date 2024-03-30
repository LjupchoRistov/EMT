package com.emt.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class NotEnoughRoomsAccommodationException extends RuntimeException {
    public NotEnoughRoomsAccommodationException(String name, Long  id) {
        super(String.format("Accommodation with name %s and id %d has no more rooms!", name, id));
    }
}
