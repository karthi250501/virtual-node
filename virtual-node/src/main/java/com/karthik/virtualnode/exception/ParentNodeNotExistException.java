package com.karthik.virtualnode.exception;

public class ParentNodeNotExistException extends RuntimeException {
    public ParentNodeNotExistException(String message) {
        super("Parent Node does not exist: " + message);
    }
}
