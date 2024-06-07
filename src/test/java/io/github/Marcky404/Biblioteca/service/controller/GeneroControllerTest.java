package io.github.Marcky404.Biblioteca.service.controller;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.Marcky404.Biblioteca.controller.GeneroController;
import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.request.GeneroRequest;
import io.github.Marcky404.Biblioteca.service.GeneroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GeneroControllerTest {

    public static final String BASE_URL = "/genero";
    public static final Long LONG_ID = 1L;

    @InjectMocks
    GeneroController controller;
    @Mock
    GeneroService service;
    MockMvc mockMvc;
    Genero genero;
    GeneroRequest generoRequest;
    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        genero = new Genero(LONG_ID, "ROMANCE");
        generoRequest = new GeneroRequest("TECNOLOGIA");

        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void salvarSucesso() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(genero))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).salvar(genero);

    }

    @Test
    void buscarSucesso() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", String.valueOf(LONG_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).buscar(LONG_ID);

    }

    @Test
    void listarTodosSucesso() throws Exception {
        mockMvc.perform(get(BASE_URL + "/listar-todos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).listarTodos();

    }

    @Test
    void atualizarSucesso() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + LONG_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(generoRequest))
                        .param("id", String.valueOf(LONG_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).atualizar(LONG_ID, generoRequest);

    }

    @Test
    void atualizarErroFamilia4xxQuandoNaoInformadoParametrosObrigatorios() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + LONG_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verifyNoInteractions(service);
    }

    @Test
    void deletarSucesso() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + LONG_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", String.valueOf(LONG_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deletar(LONG_ID);

    }

    @Test
    void deletarErroFamilia4xxQuandoNaoInformadoParametrosObrigatorios() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + LONG_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verifyNoInteractions(service);
    }
}
