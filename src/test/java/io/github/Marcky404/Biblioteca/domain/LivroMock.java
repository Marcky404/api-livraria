package io.github.Marcky404.Biblioteca.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.Marcky404.Biblioteca.domain.request.LivroRequest;

import java.io.File;
import java.io.IOException;

public class LivroMock {

    private static final String BASE_TEST_RESOURCES_PATH = "src/test/resources/json/";
    public static final Long LONG_ID = 1L;

    public static Livro getLivro() {


        Livro livro;

        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "livro_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            livro = objectMapper.readValue(jsonFile, Livro.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return livro;
    }

    public static LivroRequest getLivroRequest() {

        LivroRequest livoRequest;

        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "livroRequest_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            livoRequest = objectMapper.readValue(jsonFile, LivroRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return livoRequest;
    }
}
