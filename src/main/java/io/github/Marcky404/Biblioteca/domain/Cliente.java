package io.github.Marcky404.Biblioteca.domain;


import io.github.Marcky404.Biblioteca.domain.enums.Sexo;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
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
    private String nome;
    private String sobrenome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Status status;
    @javax.validation.constraints.Email
    private String email;
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
