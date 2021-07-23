package br.com.conexa.imedicina.desafio.weblayer;

import br.com.conexa.imedicina.desafio.controller.PacienteController;
import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteDto;
import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import br.com.conexa.imedicina.desafio.mapper.PacienteMapper;
import br.com.conexa.imedicina.desafio.security.JwtTokenProvider;
import br.com.conexa.imedicina.desafio.service.PacienteService;
import br.com.conexa.imedicina.desafio.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PacienteController.class)
public class PacienteWebLayerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PacienteService pacienteService;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    DateUtil dateUtil;

    @MockBean
    AuthenticationManager authenticationManagerBean;

    @BeforeEach
    public void setupAuthMock() {
        Mockito.when(jwtTokenProvider.resolveToken(Mockito.any())).thenReturn("faketoken");
        Mockito.when(jwtTokenProvider.validateToken("faketoken")).thenReturn(true);
        Mockito.when(jwtTokenProvider.getAuthentication("faketoken"))
                .thenReturn(new UsernamePasswordAuthenticationToken(null, "", new ArrayList<>()));
    }

    @Test
    public void listPacienteWithSuccess() throws Exception {
        PacienteDto pacienteDto = PacienteDto.builder()
                .cpf("111111")
                .fullName("Guilherme Meira")
                .onlineAccessStatus(AccessStatus.ONLINE)
                .totalAppointment(1)
                .build();

        Mockito.when(pacienteService.listAll()).thenReturn(List.of(pacienteDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("Guilherme Meira"));
    }

    @Test
    public void findByIdWithSuccess() throws Exception {
        Paciente paciente = Paciente.builder()
                .cpf("111111")
                .fullName("Guilherme Meira")
                .onlineAccessStatus(AccessStatus.ONLINE)
                .totalAppointment(1)
                .build();

        Mockito.when(pacienteService.findById(1L)).thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Guilherme Meira"));
    }

    @Test
    public void saveWithSuccess() throws Exception {
        Paciente paciente = Paciente.builder()
                .cpf("111111")
                .fullName("Guilherme Meira")
                .onlineAccessStatus(AccessStatus.ONLINE)
                .totalAppointment(1)
                .id(1L)
                .build();

        PacientePostDto pacienteDto = PacientePostDto.builder()
                .cpf("111111")
                .fullName("Guilherme Meira")
                .convenioId(1)
                .password("1234")
                .username("gui")
                .build();

        Mockito.when(pacienteService.save(pacienteDto)).thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .content(this.mapper.writeValueAsBytes(pacienteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Guilherme Meira"));
    }
}
