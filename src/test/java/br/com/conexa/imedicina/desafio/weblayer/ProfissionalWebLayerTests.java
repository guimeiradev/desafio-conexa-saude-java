package br.com.conexa.imedicina.desafio.weblayer;

import br.com.conexa.imedicina.desafio.controller.ProfissionalController;
import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPostDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteDto;
import br.com.conexa.imedicina.desafio.dto.response.ProfissionalDto;
import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import br.com.conexa.imedicina.desafio.enumerable.AvailabilityStatus;
import br.com.conexa.imedicina.desafio.security.JwtTokenProvider;
import br.com.conexa.imedicina.desafio.service.ProfissionalService;
import br.com.conexa.imedicina.desafio.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfissionalController.class)
public class ProfissionalWebLayerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ProfissionalService profissionalService;

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
    public void listProfissionalWithSuccess() throws Exception {
        ProfissionalDto profissionalDto = ProfissionalDto.builder()
                .fullName("Guilherme Meira")
                .crm("1234")
                .id(1L)
                .convenios(new HashSet<>())
                .status(AvailabilityStatus.DISPONIVEL)
                .build();

        Mockito.when(profissionalService.listAll()).thenReturn(List.of(profissionalDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/profissionais")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("Guilherme Meira"));
    }

    @Test
    public void findByIdWithSuccess() throws Exception {
        Profissional profissional = Profissional.builder()
                .fullName("Guilherme Meira")
                .crm("1234")
                .id(1L)
                .convenios(new HashSet<>())
                .status(AvailabilityStatus.DISPONIVEL)
                .build();

        Mockito.when(profissionalService.findById(1L)).thenReturn(profissional);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissionais/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Guilherme Meira"));
    }

    @Test
    public void saveWithSuccess() throws Exception {

        ProfissionalPostDto profissionalPostDto = ProfissionalPostDto.builder()
                .fullName("Guilherme Meira")
                .crm("1234")
                .build();

        ProfissionalDto profissionalResponseDto = ProfissionalDto.builder()
                .fullName("Guilherme Meira")
                .crm("1234")
                .build();

        Mockito.when(profissionalService.save(profissionalPostDto)).thenReturn(profissionalResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/profissionais")
                .content(this.mapper.writeValueAsBytes(profissionalPostDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Guilherme Meira"));
    }

}
