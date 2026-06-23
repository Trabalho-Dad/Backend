package com.dad.sales_api.shared.exceptions;

import org.springframework.http.HttpStatus;

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
