package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.enums.MensagemErro;
import io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest;
import io.github.Marcky404.Biblioteca.domain.response.EnderecoResponse;
import io.github.Marcky404.Biblioteca.repository.ClienteRepository;
import io.github.Marcky404.Biblioteca.repository.EnderecoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class EnderecoService {


    @Value("${endpoint.cep}")
    private String endpoint;
    private final EnderecoRepository repository;
    private final ClienteRepository clienteRepository;

    private final RestTemplate restTemplate;

    public Endereco buscar(Long id) {
        return repository.findById(id).orElseThrow(MensagemErro.ENDERECO_NAO_ENCONTRADO::asBusinessException);
    }

    public Endereco buscarEndereco(String cep) {
        String uri = getUrlViaCep(cep);
        return restTemplate.getForObject(uri, Endereco.class);
    }

    @NotNull
    private String getUrlViaCep(String cep) {
        return UriComponentsBuilder.fromHttpUrl(endpoint + cep + "/json")
                .build(false)
                .toUriString();
    }

    @Transactional
    public EnderecoResponse atualizar(Long id, EnderecoRequest enderecoRequest) {
        Endereco endereco = buscar(id);

        if (!endereco.getCep().equals(enderecoRequest.getCep())) {
            enderecoRequest = construirEndereco(enderecoRequest);
        }

        endereco.setCep(enderecoRequest.getCep());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setUf(enderecoRequest.getUf());
        endereco.setLocalidade(enderecoRequest.getLocalidade());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setDestinatario(enderecoRequest.getDestinatario());

        return new EnderecoResponse(endereco);
    }


    private EnderecoRequest construirEndereco(EnderecoRequest enderecoRequest) {
        Endereco enderecoViaCep = buscarEndereco(enderecoRequest.getCep());

        enderecoRequest.setCep(enderecoViaCep.getCep());
        enderecoRequest.setUf(enderecoViaCep.getUf());
        enderecoRequest.setBairro(enderecoViaCep.getBairro());
        enderecoRequest.setLogradouro(enderecoViaCep.getLogradouro());
        enderecoRequest.setLocalidade(enderecoViaCep.getLocalidade());

        return enderecoRequest;
    }

    public void deletar(Long enderecoId, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(MensagemErro.CLIENTE_NAO_ENCONTRADO::asBusinessException);

        if (cliente.getEnderecos().size() == 1) {
            throw MensagemErro.ENDERECO_NAO_DELETADO.asBusinessException();
        }
        Endereco endereco = buscar(enderecoId);
        cliente.getEnderecos().remove(endereco);
        clienteRepository.save(cliente);

    }
}
