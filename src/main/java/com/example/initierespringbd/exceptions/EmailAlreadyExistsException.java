package com.example.initierespringbd.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("EMAIL_ALREADY_EXISTS_EXCEPTION");
    }
}
