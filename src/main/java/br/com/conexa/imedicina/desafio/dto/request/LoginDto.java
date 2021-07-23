package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String username;
    private String password;

}
