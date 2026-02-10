package edu.unl.cc.smilehub.exception;

public class CredentialInvalidException extends Exception{

    public CredentialInvalidException() {
        super("Credenciales invalidas");
    }

    public CredentialInvalidException(String message) {
        super(message);
    }

    public CredentialInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}

