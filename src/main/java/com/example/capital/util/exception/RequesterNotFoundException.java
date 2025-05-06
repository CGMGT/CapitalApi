package com.example.capital.util.exception;

public class RequesterNotFoundException extends Exception {

    public RequesterNotFoundException() {
        super("El solicitante no existe en el sistema.");
    }

}
