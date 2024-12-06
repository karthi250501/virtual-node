package com.karthik.virtualnode.exception;

public class NodesAlreadyExistException extends RuntimeException {
    public NodesAlreadyExistException(String message) {
        super("Nodes Already Exist: " + message);
    }
}
