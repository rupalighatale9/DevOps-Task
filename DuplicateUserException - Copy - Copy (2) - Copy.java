package com.usermanagement.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String message) {
        super(message);
    }

//    public DuplicateUserException(String email) {
//        super("User already exists with email: " + email);
//    }
}
