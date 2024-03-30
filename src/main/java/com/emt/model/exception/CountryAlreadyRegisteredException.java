package com.emt.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class CountryAlreadyRegisteredException extends RuntimeException{
    public CountryAlreadyRegisteredException(String name) {
        super(String.format("Country with name: %s is already in database!", name));
    }
}