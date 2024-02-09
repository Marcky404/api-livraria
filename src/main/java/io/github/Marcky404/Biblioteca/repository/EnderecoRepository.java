package io.github.Marcky404.Biblioteca.repository;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface EnderecoRepository extends JpaRepository<Endereco,Long> {



}
