package com.example.creditcardservice.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(String msg, Throwable causa){
        super(msg, causa);
    }

}
