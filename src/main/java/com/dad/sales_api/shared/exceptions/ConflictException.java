package com.dad.sales_api.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends SalesException {
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable error) {
        super(message, error);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
