package ru.restaurant_voting.error;

import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import static org.springframework.boot.web.error.ErrorAttributeOptions.of;

public class IllegalRequestDataException extends AppException {

    public IllegalRequestDataException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg, of(Include.MESSAGE));
    }
}
