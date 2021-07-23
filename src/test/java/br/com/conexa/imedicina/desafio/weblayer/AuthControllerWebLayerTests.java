package br.com.conexa.imedicina.desafio.weblayer;

import br.com.conexa.imedicina.desafio.controller.AuthController;
import br.com.conexa.imedicina.desafio.dto.request.LoginDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.security.JwtTokenProvider;
import br.com.conexa.imedicina.desafio.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerWebLayerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AuthService authService;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    AuthenticationManager authenticationManagerBean;

    @Test
    public void post_LoginWithSuccess() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .password("1234")
                .username("username")
                .build();
        Mockito.when(authService.login(loginDto)).thenReturn("faketoken");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").content(mapper.writeValueAsBytes(loginDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("faketoken"));
    }

    @Test
    public void post_SignupWithSuccess() throws Exception {
        PacientePostDto pacientePostDto = PacientePostDto.builder()
                .password("1234")
                .username("username")
                .convenioId(0)
                .cpf("1111111")
                .fullName("Guilherme Meira")
                .build();
        Mockito.when(authService.signup(pacientePostDto)).thenReturn("faketoken");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/cadastrar").content(mapper.writeValueAsBytes(pacientePostDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("faketoken"));
    }
}