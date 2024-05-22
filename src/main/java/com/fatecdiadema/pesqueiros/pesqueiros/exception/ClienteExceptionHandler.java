package com.fatecdiadema.pesqueiros.pesqueiros.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ClienteExceptionHandler extends RuntimeException {

    public ClienteExceptionHandler() {
        super();
    }

    public ClienteExceptionHandler(String message) {
        super(String.format("Campo digitado é inválido!", message));
    }

    public ClienteExceptionHandler(String message, Throwable cause) {
        super(String.format("Campo digitado é inválido!" ,message, cause));
    }
}
