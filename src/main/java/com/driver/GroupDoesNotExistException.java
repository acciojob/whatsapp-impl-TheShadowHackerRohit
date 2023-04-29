package com.driver;

public class GroupDoesNotExistException extends RuntimeException {
    public GroupDoesNotExistException() {
        super("Group does not exist");
    }
}
