package edu.unl.cc.smilehub.exception;
/*
**@ author Kleyner.ls
 */

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

