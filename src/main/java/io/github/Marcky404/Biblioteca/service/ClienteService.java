package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.enums.MensagemErro;
import io.github.Marcky404.Biblioteca.domain.request.ClienteAtualizarRequest;
import io.github.Marcky404.Biblioteca.domain.request.ClienteRequest;
import io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest;
import io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest;
import io.github.Marcky404.Biblioteca.domain.response.ClienteResponse;
import io.github.Marcky404.Biblioteca.repository.ClienteRepository;
import io.github.Marcky404.Biblioteca.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final EnderecoService enderecoService;

    public Cliente salvar(ClienteRequest clienteRequest) {

        List<TelefoneRequest> telefoneRequestList = clienteRequest.getTelefones().stream().map(this::construirTelefone).toList();
        List<EnderecoRequest> enderecoRequestList = clienteRequest.getEnderecos().stream().map(this::construirEndereco).toList();

        clienteRequest.setTelefones(telefoneRequestList);
        clienteRequest.setEnderecos(enderecoRequestList);

        Cliente cliente = clienteRequest.converterParaEntidade();
        return repository.save(cliente);
    }

    private TelefoneRequest construirTelefone(TelefoneRequest telefoneRequest) {
        TelefoneRequest tel = new TelefoneRequest();
        tel.setDdd(telefoneRequest.getDdd());
        tel.setNumero(Utils.validarNumero(telefoneRequest.getNumero(), telefoneRequest.getTipoTelefone()));
        tel.setTipoTelefone(telefoneRequest.getTipoTelefone());

        return tel;
    }

    public EnderecoRequest construirEndereco(EnderecoRequest enderecoRequest) {
        Endereco enderecoViaCep = enderecoService.buscarEndereco(enderecoRequest.getCep());
        EnderecoRequest end = new EnderecoRequest();

        end.setNumero(enderecoRequest.getNumero());
        end.setComplemento(enderecoRequest.getComplemento());
        end.setDestinatario(enderecoRequest.getDestinatario());

        end.setCep(enderecoViaCep.getCep());
        end.setUf(enderecoViaCep.getUf());
        end.setBairro(enderecoViaCep.getBairro());
        end.setLogradouro(enderecoViaCep.getLogradouro());
        end.setLocalidade(enderecoViaCep.getLocalidade());

        return end;
    }

    public Cliente buscar(Long id) {
        return repository.findById(id).orElseThrow(MensagemErro.CLIENTE_NAO_ENCONTRADO::asBusinessException);
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteAtualizarRequest clienteAtualizarRequest) {

        Cliente cliente = buscar(id);

        cliente.setNome(clienteAtualizarRequest.getNome());
        cliente.setSobrenome(clienteAtualizarRequest.getSobrenome());
        cliente.setEmail(clienteAtualizarRequest.getEmail());

        return new ClienteResponse(cliente);

    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
