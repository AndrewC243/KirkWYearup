package org.yearup.exceptions;

public class PrincipalNotFoundException extends RuntimeException{
    public PrincipalNotFoundException(String message) {
        super(message);
    }
}
