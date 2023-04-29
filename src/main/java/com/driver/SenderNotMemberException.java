package com.driver;

public class SenderNotMemberException extends RuntimeException{
    public SenderNotMemberException() {
        super("You are not allowed to send message");
    }
}
