package org.example.jpa_jpql.Api;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
