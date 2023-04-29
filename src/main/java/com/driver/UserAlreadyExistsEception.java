package com.driver;

class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
