package com.LibroFlow.demo.infra.exceptions;

public class EventIllegalArgumentException extends RuntimeException {
    public EventIllegalArgumentException(String message) { super(message); }
    public EventIllegalArgumentException() { super("Event illegal argument"); }
}
