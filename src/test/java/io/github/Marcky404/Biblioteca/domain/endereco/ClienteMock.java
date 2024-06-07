package io.github.Marcky404.Biblioteca.domain.endereco;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.Marcky404.Biblioteca.domain.Cliente;

import java.io.File;
import java.io.IOException;

public class ClienteMock {
    private static final String BASE_TEST_RESOURCES_PATH = "src/test/resources/json/";
    public static final Long LONG_ID = 1L;



    public static Cliente getCliente() {



        Cliente cliente;

        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "cliente_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            cliente = objectMapper.readValue(jsonFile, Cliente.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }
}
