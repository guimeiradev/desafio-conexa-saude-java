package br.com.conexa.imedicina.desafio.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {

    private HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

}