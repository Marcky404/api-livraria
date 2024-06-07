package io.github.Marcky404.Biblioteca.domain.endereco;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import io.github.Marcky404.Biblioteca.domain.Endereco;


public class EnderecoMock {
    private static final String BASE_TEST_RESOURCES_PATH = "src/test/resources/json/";
    public static final Long LONG_ID = 1L;
    public static  final String CEP = "40285-001";
    public static Endereco getEndereco() {

        Endereco endereco;


        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "endereco_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            endereco = objectMapper.readValue(jsonFile, Endereco.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return endereco;
    }

    public static Endereco getEnderecoViaCep() {

        Endereco enderecoViaCep;


        File jsonFile = new File(BASE_TEST_RESOURCES_PATH + "enderecoViaCep_successful.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            enderecoViaCep = objectMapper.readValue(jsonFile, Endereco.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return enderecoViaCep;
    }


}

