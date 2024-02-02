package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Livro;
import io.github.Marcky404.Biblioteca.domain.enuns.Genero;
import io.github.Marcky404.Biblioteca.domain.request.LivroLoteRequest;
import io.github.Marcky404.Biblioteca.domain.request.LivroQuantidadeTotalGenero;
import io.github.Marcky404.Biblioteca.domain.request.LivroRequest;
import io.github.Marcky404.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;


    public void salvar(Livro livro) {
        repository.save(livro);
    }

    public Livro buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado para o id: " + id));
    }


    @Transactional
    public Livro atualiar(Long id, LivroRequest livroRequest) {
        Livro livro = buscar(id);

        livro.setTitulo(livroRequest.getTitulo());
        livro.setAutor(livroRequest.getAutor());
        livro.setNumeroPaginas(livroRequest.getNumeroPaginas());
        livro.setGenero(livroRequest.getGenero());
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

    public List<Livro> buscarListaDeLivrosPorGenero(Genero genero){
        return repository.findByLivroByGenero(genero);
    }

   public List<LivroQuantidadeTotalGenero> buscarLivroQuantidadeTotalGenero(){
        return repository.findByLivroQuantidadeTotalGenero();
   }
}
