package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.Livro;
import io.github.Marcky404.Biblioteca.domain.enums.MensagemErro;
import io.github.Marcky404.Biblioteca.domain.request.LivroLoteRequest;
import io.github.Marcky404.Biblioteca.domain.request.LivroQuantidadeTotalGenero;
import io.github.Marcky404.Biblioteca.domain.request.LivroRequest;
import io.github.Marcky404.Biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {


    private final LivroRepository repository;
    private final GeneroService generoService;


    public void salvar(Livro livro) {
        repository.save(livro);


    }

    public Livro buscar(Long id) {
        return repository.findById(id).orElseThrow(MensagemErro.LIVRO_NAO_ENCONTRADO::asBusinessException);
    }


    @Transactional
    public Livro atualizar(Long id, LivroRequest livroRequest) {

        Livro livro = buscar(id);
        Genero genero = generoService.buscar(livroRequest.getGenero().getId());

        livro.setTitulo(livroRequest.getTitulo());
        livro.setAutor(livroRequest.getAutor());
        livro.setNumeroPaginas(livroRequest.getNumeroPaginas());
        livro.setGenero(genero);
        livro.setIsbn(livroRequest.getIsbn());
        livro.setDisponibilidade(livroRequest.getDisponibilidade());
        livro.setValor(livroRequest.getValor());

        return livro;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public void salvarEmLote(LivroLoteRequest livroLoteRequest) {
        repository.saveAll(livroLoteRequest.getLivros());
    }

    public List<Livro> buscarListaDeLivrosOrdenadoPorMaiorValor() {
        return repository.findByOrderByValorDesc();
    }

    public List<Livro> buscarListaDeLivrosOrdenadoPorMenorValor() {
        return repository.findByLivroOrderByValorAsc();
    }

    public List<Livro> buscarListaDeLivrosPorGenero(Long generoID) {
        return repository.findByLivroByGenero(generoID);
    }

    public List<LivroQuantidadeTotalGenero> buscarLivroQuantidadeTotalGenero() {
        return repository.findByLivroQuantidadeTotalGenero();
    }
}
