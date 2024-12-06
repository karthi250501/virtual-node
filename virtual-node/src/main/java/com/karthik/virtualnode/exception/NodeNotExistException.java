package com.karthik.virtualnode.exception;

public class NodeNotExistException extends RuntimeException{
    public NodeNotExistException(String message) {
        super("Node does not exist: " + message);
    }
}
