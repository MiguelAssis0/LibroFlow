package com.LibroFlow.demo.infra.exceptions;

public class EventBadRequest extends RuntimeException {
    public EventBadRequest() { super("Event bad request"); }
    public EventBadRequest(String message) { super(message); }
}
