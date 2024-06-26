package br.edu.up.CinemaManager.exceptions;

public class SessaoNotFoundException extends RuntimeException {
    public SessaoNotFoundException(String message) {
        super(message);
    }
}
