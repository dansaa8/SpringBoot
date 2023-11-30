package com.example.springboot.exception;

public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String message) {super(message);}
}