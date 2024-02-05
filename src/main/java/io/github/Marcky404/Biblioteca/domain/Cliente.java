package io.github.Marcky404.Biblioteca.domain;


import io.github.Marcky404.Biblioteca.domain.enums.Sexo;
import io.github.Marcky404.Biblioteca.utils.Utils;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf", name = "uk_cliente_cpf")})
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Telefone> telefones;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos;

    public Cliente(String nome, String sobrenome, String cpf, Sexo sexo, LocalDate dataNascimento, List<Telefone> telefones, List<Endereco> enderecos) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }


}
