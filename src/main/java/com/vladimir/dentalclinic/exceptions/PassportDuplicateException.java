package com.vladimir.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Пользователь с таким паспортом уже существует")
public class PassportDuplicateException extends Exception {

    public PassportDuplicateException(String message) {
        super(message);
    }

}
