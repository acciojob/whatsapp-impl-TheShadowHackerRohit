package com.driver;

public class ApproverNotAdminException extends RuntimeException {
    public ApproverNotAdminException() {
        super("Approver does not have rights");
    }
}

