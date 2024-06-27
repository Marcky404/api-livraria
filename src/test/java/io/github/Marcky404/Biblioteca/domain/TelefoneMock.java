package io.github.Marcky404.Biblioteca.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest;

import java.io.File;
import java.io.IOException;

public class TelefoneMock {

    private static final String BASE_TEST_RESOURCES_PATH = "src/test/resources/json/";
    public static final Long LONG_ID = 1L;

    public static Telefone getTelefone() {


        Telefone telefone;


        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "telefone_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            telefone = objectMapper.readValue(jsonFile, Telefone.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return telefone;
    }
    public static TelefoneRequest getTelefoneRequest() {


        TelefoneRequest telefoneRequest;


        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "telefoneRequest_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            telefoneRequest = objectMapper.readValue(jsonFile, TelefoneRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return telefoneRequest;
    }
}
