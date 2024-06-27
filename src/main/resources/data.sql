INSERT INTO livro_genero (id, genero) VALUES
(1, 'TERROR'),
(2, 'ROMANCE'),
(3, 'FICCAO'),
(4, 'TECNOLOGIA'),
(5, 'AVENTURA'),
(6, 'ARTES');

INSERT INTO livro (id, titulo, autor, ano_publicacao, isbn, genero_id, numero_paginas, disponibilidade, valor, created) VALUES
(1,'Viagem ao Centro da Terra', 'Júlio Verne', 1864, '978-85-66210-07-1', 1, 304, 'INDISPONIVEL', 30.00, '2024-05-23');

INSERT INTO cliente (id, nome, sobrenome, cpf, status, email, sexo, data_nascimento) VALUES
(1, 'João', 'Santana', '92850646040', 'ATIVO', 'joao@gmail.com', 'MASCULINO', '2000-02-14');

INSERT INTO telefone (id, cliente_id, ddd, numero, tipo_telefone) VALUES
(1, 1, 12, '22222222', 'RESIDENCIAL'),
(2, 1, 41, '33332222', 'COMERCIAL'),
(3, 1, 41, '985478541', 'CELULAR');

INSERT INTO endereco (id, cliente_id, cep, numero, complemento, destinatario) VALUES
(1, 1, '23932-490', 200, 'complemento', 'Maria Silva'),
(2, 1, '43900-000', 201, 'complemento', 'José Souza'),
(3, 1, '23932-490', 202, 'complemento', 'Ana Pereira');
