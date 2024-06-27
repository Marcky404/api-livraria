package io.github.Marcky404.Biblioteca.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.Marcky404.Biblioteca.domain.request.ClienteAtualizarRequest;
import io.github.Marcky404.Biblioteca.domain.request.ClienteRequest;

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

    public static ClienteRequest getClienteRequest() {


        ClienteRequest clienteRequest;

        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "clienteRequest_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            clienteRequest = objectMapper.readValue(jsonFile, ClienteRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clienteRequest;
    }

    public static ClienteAtualizarRequest getClienteAtualizarRequest() {


        ClienteAtualizarRequest clienteAtualizarRequest;

        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "clienteAtualizarRequest_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            clienteAtualizarRequest = objectMapper.readValue(jsonFile, ClienteAtualizarRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clienteAtualizarRequest;
    }


}
