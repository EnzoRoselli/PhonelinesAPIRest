package com.utn.UTNphones.exceptions;

public class SearchByDatesException extends RuntimeException {

    private final String message;

    public SearchByDatesException() {
        this.message = "Invalid dates";
    }

    @Override
    public String getMessage() {
        return message;
    }
}