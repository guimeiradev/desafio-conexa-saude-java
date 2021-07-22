package br.com.conexa.imedicina.desafio.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProfessionalAlreadyInUseException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "Profissional já está com a agenda marcada nesse horário e data.";

    public ProfessionalAlreadyInUseException(){
        super(MESSAGE);
    }

}