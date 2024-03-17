package com.panem.panem_backend.DTO;


public class Response {
    public String getMessage() {
        return message;
    }

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}

