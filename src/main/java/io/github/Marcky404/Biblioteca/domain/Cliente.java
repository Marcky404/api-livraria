package io.github.Marcky404.Biblioteca.domain;


import io.github.Marcky404.Biblioteca.domain.enums.Sexo;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Schema(title = "Nome do cliente", example = "João", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;
    @Schema(title = "Sobrenome do cliente", example = "Da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sobrenome;
    @Schema(title = "CPF do cliente", example = "123.456.789-00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;
    @Enumerated(EnumType.STRING)
    @Schema(title = "Status do cliente", example = "ATIVO", requiredMode = Schema.RequiredMode.REQUIRED)
    private Status status;
    @Schema(title = "E-mail do cliente", example = "joao@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Enumerated(EnumType.STRING)
    @Schema(title = "Gênero do cliente", example = "MASCULINO", requiredMode = Schema.RequiredMode.REQUIRED)
    private Sexo sexo;
    @Column(name = "data_nascimento")
    @Schema(title = "Data de nascimento do cliente", example = "2000-10-08", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate dataNascimento;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    @Schema(title = "Telefones do cliente", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Telefone> telefones;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    @Schema(title = "Endereços do cliente", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Endereco> enderecos;

    public Cliente(String nome, String sobrenome, String cpf, Status status, String email, Sexo sexo, LocalDate dataNascimento, List<Telefone> telefones, List<Endereco> enderecos) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.status = status;
        this.email = email;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }
}
