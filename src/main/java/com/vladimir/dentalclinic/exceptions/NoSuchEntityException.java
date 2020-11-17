package com.vladimir.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Such entity is not found")
public class NoSuchEntityException extends Exception {

    public NoSuchEntityException(String message) {
        super(message);
    }

}
