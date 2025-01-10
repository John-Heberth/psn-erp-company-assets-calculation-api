package com.calculation.pension.erppension.domain.exception;

/**
 * Classe para exceções de negócio
 *
 */
public class BusinessException extends RuntimeException {

    public BusinessException (String message){super (message);}

    public BusinessException (String message, Throwable cause){super (message, cause);}

}
