package com.LibroFlow.demo.infra.exceptions;

public class EventFullException extends RuntimeException{
    public EventFullException() { super("Event full"); }

    public EventFullException(String message) { super(message); }
}
