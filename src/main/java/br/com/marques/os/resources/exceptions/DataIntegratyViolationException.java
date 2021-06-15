package br.com.marques.os.resources.exceptions;

public class DataIntegratyViolationException extends  RuntimeException{

    public DataIntegratyViolationException (String message) {
        super(message);
    }

}