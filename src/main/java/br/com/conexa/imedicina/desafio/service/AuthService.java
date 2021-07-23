package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.LoginDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.exception.CustomException;
import br.com.conexa.imedicina.desafio.mapper.PacienteMapper;
import br.com.conexa.imedicina.desafio.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final PacienteService pacienteService;
    private final ConvenioService convenioService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {
        Paciente paciente = pacienteService.findByUsername(loginDto.getUsername());

        if (paciente == null)
            throw new CustomException("Usuário/Paciente não existente!", HttpStatus.NOT_FOUND);
        try {
            List<GrantedAuthority> role = new ArrayList<>();
            role.add((GrantedAuthority) () -> "Paciente");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(paciente.getUsername(), loginDto.getPassword(), role));
            pacienteService.setPacienteStatusOnline(paciente);
            return jwtTokenProvider.createToken(paciente.getUsername());
        } catch (AuthenticationException e) {
            throw new CustomException("Credenciais incorretas!", HttpStatus.UNAUTHORIZED);
        }

    }


    public String signup(PacientePostDto pacientePostDto) {
        if (pacienteService.existsByUsername(pacientePostDto.getUsername())) {
            throw new CustomException("Paciente já cadastrado com esse usuário!", HttpStatus.BAD_REQUEST);
        }
        Paciente paciente = PacienteMapper.toEntity(pacientePostDto);
        paciente.setPassword(this.passwordEncoder.encode(pacientePostDto.getPassword()));
        paciente.setConvenio(convenioService.findById(pacientePostDto.getConvenioId()));

        this.pacienteService.save(paciente);
        return this.jwtTokenProvider.createToken(pacientePostDto.getUsername());
    }
}
