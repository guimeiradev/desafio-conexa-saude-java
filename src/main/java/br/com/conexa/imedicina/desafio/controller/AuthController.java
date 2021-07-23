package br.com.conexa.imedicina.desafio.controller;

import br.com.conexa.imedicina.desafio.dto.request.LoginDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.response.TokenResponse;
import br.com.conexa.imedicina.desafio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<TokenResponse> signup(@RequestBody PacientePostDto pacientePostDto) {
        return new ResponseEntity<>(new TokenResponse(authService.signup(pacientePostDto)), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(new TokenResponse(authService.login(loginDto)), HttpStatus.OK);
    }

}
