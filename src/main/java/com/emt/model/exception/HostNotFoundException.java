package com.emt.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class HostNotFoundException extends RuntimeException{
    public HostNotFoundException(Long id) {
        super(String.format("Host with id: %d is not found", id));
    }

    public HostNotFoundException(String attribute) {
        super(String.format("Host with name/surname: %s is not found", attribute));
    }

    public HostNotFoundException(String name, String surname) {
        super(String.format("Host with name/surname: %s/%s is not found", name, surname));
    }
}