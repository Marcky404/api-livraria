package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.request.ClienteRequest;
import io.github.Marcky404.Biblioteca.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public void salvar(Cliente cliente) {
        repository.save(cliente);
    }

    public Cliente buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não localizado!"));
    }

    @Transactional
    public Cliente atualizar(Long id, ClienteRequest clienteRequest) {

        Cliente cliente = buscar(id);

        cliente.setNome(clienteRequest.getNome());
        cliente.setSobrenome(clienteRequest.getSobrenome());
        cliente.setCpf(clienteRequest.getCpf());
        cliente.setSexo(clienteRequest.getSexo());
        cliente.setTelefones(clienteRequest.getTelefones());
        cliente.setEnderecos(clienteRequest.getEnderecos());
        cliente.setDataNascimento(clienteRequest.getDataNascimento());

        return cliente;

    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
