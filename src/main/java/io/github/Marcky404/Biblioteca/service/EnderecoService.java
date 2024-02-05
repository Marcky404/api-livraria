package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {


    @Value("${endpoint.cep}")
    private String endpoint;
    private  RestTemplate restTemplate = new RestTemplate();

    public Endereco buscarEndereço(String cep){
        return restTemplate.getForObject(endpoint+cep + "/json", Endereco.class);
    }
}
