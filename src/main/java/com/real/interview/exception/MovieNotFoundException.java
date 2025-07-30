package com.real.interview.exception;

public class MovieNotFoundException extends RuntimeException{

    private static final long serialVersionId = 1009L;

    public MovieNotFoundException(){
        super();
    }

    public MovieNotFoundException(String msg){
        super(msg);
    }

    public MovieNotFoundException(String msg, Throwable th){
        super(msg,th);
    }

    public MovieNotFoundException(String msg, Exception exception){
        super(msg,exception);
    }
}
