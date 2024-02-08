package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enums.MensagemErro;
import io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest;
import io.github.Marcky404.Biblioteca.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TelefoneService {

    public final TelefoneRepository repository;


    public Telefone buscar(Long id) {
        return repository.findById(id).orElseThrow(MensagemErro.TELEFONE_NAO_ENCONTRADO::asBusinessException);
    }


    @Transactional
    public TelefoneRequest atualizar(Long id, TelefoneRequest telefoneRequest) {
        Telefone telefone = buscar(id);

        telefone.setDdd(telefoneRequest.getDdd());
        telefone.setNumero(telefoneRequest.getNumero());
        telefone.setTipoTelefone(telefoneRequest.getTipoTelefone());

        return new TelefoneRequest(telefone);

    }

}
