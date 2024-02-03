package io.github.Marcky404.Biblioteca.repository;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
