package com.karthik.virtualnode.exception;

public class GroupAlreadyExistException extends RuntimeException{
    public GroupAlreadyExistException(String message) {
        super("Group Already Exist: " + message);
    }
}
