-- Criando o banco de dados
DROP DATABASE IF EXISTS sollares;
CREATE DATABASE sollares;
USE sollares;

-- Criação da tabela tb_pessoa
CREATE TABLE tb_pessoa (
    id_pessoa INT NOT NULL AUTO_INCREMENT,
    nome_pessoa VARCHAR(255),
    endereco VARCHAR(255),
    uf VARCHAR(2),
    telefone VARCHAR(15),
    cpf VARCHAR(14),
    email VARCHAR(255),
    PRIMARY KEY (id_pessoa)
);

-- Criação da tabela tb_disciplina
CREATE TABLE tb_disciplina (
    codigo INT NOT NULL AUTO_INCREMENT,
    nome_disciplina VARCHAR(255),
    carga_horaria INT,
    limite_alunos INT,
    id_professor INT,
    PRIMARY KEY (codigo),
    FOREIGN KEY (id_professor) REFERENCES tb_pessoa(id_pessoa)
);

-- Criação da tabela tb_matricula
CREATE TABLE tb_matricula (
    id_mat INT NOT NULL AUTO_INCREMENT,
    data_matricula DATE,
    valor_pago DECIMAL(10, 2),
    periodo VARCHAR(10),
    id_disciplina INT,
    id_aluno INT,
    PRIMARY KEY (id_mat),
    FOREIGN KEY (id_disciplina) REFERENCES tb_disciplina(codigo),
    FOREIGN KEY (id_aluno) REFERENCES tb_pessoa(id_pessoa)
);

-- Criação da tabela tb_usuario
CREATE TABLE tb_usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    cargo VARCHAR(100),
    login VARCHAR(50) unique,
    senha VARCHAR(100),
    email VARCHAR(255),
    PRIMARY KEY (id_usuario)
);


-- Administradores do sistema
INSERT INTO tb_usuario (nome, cargo, login, senha, email) 
VALUES 
    ('Layla dos Santos Nascimento', 'Administrador', 'layla', '1234', 'layla@exemplo.com'),
    ('Jonas Gabriel', 'Administrador', 'admin', 'admin', 'jonas@exemplo.com');
    
    INSERT INTO tb_pessoa (nome_pessoa, endereco, uf, telefone, cpf, email)
VALUES 
('Pedro', 'Rua das Flores, 123', 'SP', '(11) 98765-4321', '123.456.789-00', 'pedro@gmail.com'),
('Luiz', 'Avenida Central, 456', 'RJ', '(21) 91234-5678', '234.567.890-11', 'luiz@gmail.com'),
('Ramon', 'Praça do Sol, 789', 'MG', '(31) 93456-7890', '345.678.901-22', 'ramon@gmail.com'),
('Felipe', 'Rua do Porto, 101', 'RS', '(51) 99876-5432', '456.789.012-33', 'felipe@gmail.com'),
('Gertrudes', 'Avenida das Palmeiras, 202', 'BA', '(71) 97654-3210', '567.890.123-44', 'gertrudes@gmail.com'),
('Ana', 'Rua A, 10', 'SP', '(11) 98888-5555', '123.321.123-45', 'ana@gmail.com'),
('Beatriz', 'Rua B, 20', 'RJ', '(21) 98888-4444', '234.432.234-56', 'beatriz@gmail.com'),
('Carlos', 'Rua C, 30', 'MG', '(31) 98888-3333', '345.543.345-67', 'carlos@gmail.com'),
('Diana', 'Rua D, 40', 'RS', '(51) 98888-2222', '456.654.456-78', 'diana@gmail.com'),
('Eduardo', 'Rua E, 50', 'BA', '(71) 98888-1111', '567.765.567-89', 'eduardo@gmail.com'),
('Fernanda', 'Rua F, 60', 'SP', '(11) 97777-1234', '678.876.678-90', 'fernanda@gmail.com'),
('Gabriel', 'Rua G, 70', 'RJ', '(21) 97777-2345', '789.987.789-01', 'gabriel@gmail.com'),
('Heloísa', 'Rua H, 80', 'MG', '(31) 97777-3456', '890.098.890-12', 'heloisa@gmail.com'),
('Igor', 'Rua I, 90', 'RS', '(51) 97777-4567', '901.109.901-23', 'igor@gmail.com'),
('Júlia', 'Rua J, 100', 'BA', '(71) 97777-5678', '012.210.012-34', 'julia@gmail.com'),
('Karla', 'Rua K, 110', 'SP', '(11) 96666-6789', '123.321.234-45', 'karla@gmail.com'),
('Luan', 'Rua L, 120', 'RJ', '(21) 96666-7890', '234.432.345-56', 'luan@gmail.com'),
('Mariana', 'Rua M, 130', 'MG', '(31) 96666-8901', '345.543.456-67', 'mariana@gmail.com'),
('Natan', 'Rua N, 140', 'RS', '(51) 96666-9012', '456.654.567-78', 'natan@gmail.com'),
('Olga', 'Rua O, 150', 'BA', '(71) 96666-0123', '567.765.678-89', 'olga@gmail.com'),
('Paulo', 'Rua P, 160', 'SP', '(11) 95555-1234', '678.876.789-90', 'paulo@gmail.com'),
('Quésia', 'Rua Q, 170', 'RJ', '(21) 95555-2345', '789.987.890-12', 'quesia@gmail.com'),
('Rafael', 'Rua R, 180', 'MG', '(31) 95555-3456', '890.098.901-23', 'rafael@gmail.com'),
('Sílvia', 'Rua S, 190', 'RS', '(51) 95555-4567', '901.109.012-34', 'silvia@gmail.com'),
('Tatiane', 'Rua T, 200', 'BA', '(71) 95555-5678', '012.210.123-45', 'tatiane@gmail.com'),
('Ulisses', 'Rua U, 210', 'SP', '(11) 94444-6789', '123.321.345-56', 'ulisses@gmail.com'),
('Valéria', 'Rua V, 220', 'RJ', '(21) 94444-7890', '234.432.456-67', 'valeria@gmail.com'),
('Wagner', 'Rua W, 230', 'MG', '(31) 94444-8901', '345.543.567-78', 'wagner@gmail.com'),
('Ximena', 'Rua X, 240', 'RS', '(51) 94444-9012', '456.654.678-89', 'ximena@gmail.com'),
('Yara', 'Rua Y, 250', 'BA', '(71) 94444-0123', '567.765.789-90', 'yara@gmail.com'),
('Zeca', 'Rua Z, 260', 'SP', '(11) 93333-1234', '678.876.890-01', 'zeca@gmail.com'),
('Amanda', 'Rua A1, 270', 'RJ', '(21) 93333-2345', '789.987.901-12', 'amanda@gmail.com'),
('Bruno', 'Rua B1, 280', 'MG', '(31) 93333-3456', '890.098.012-23', 'bruno@gmail.com'),
('Clara', 'Rua C1, 290', 'RS', '(51) 93333-4567', '901.109.123-34', 'clara@gmail.com');

INSERT INTO tb_disciplina (nome_disciplina, carga_horaria, limite_alunos, id_professor) 
VALUES 
    ('Matemática', 200, 40, 2),
    ('Português', 200, 40, 2),
    ('História', 150, 35, 1),
    ('Geografia', 150, 35, 3),
    ('Ciências', 180, 30, 2),
    ('Educação Física', 100, 45, 3);
    
INSERT INTO tb_matricula (data_matricula, valor_pago, periodo, id_disciplina, id_aluno)
VALUES
('2024-11-01', 500.00, 'Matutino', 2, 1),  -- Matemática
('2024-11-02', 450.00, 'Vespertino', 2, 2),  -- Português
('2024-11-03', 400.00, 'Noturno', 1, 3),  -- História
('2024-11-04', 450.00, 'Matutino', 3, 4),  -- Geografia
('2024-11-05', 420.00, 'Vespertino', 2, 5),  -- Ciências
('2024-11-06', 380.00, 'Noturno', 6, 6),  -- Educação Física
('2024-11-07', 500.00, 'Matutino', 2, 7),  -- Matemática
('2024-11-08', 450.00, 'Vespertino', 2, 8),  -- Português
('2024-11-09', 400.00, 'Noturno', 1, 9),  -- História
('2024-11-10', 450.00, 'Matutino', 3, 10),  -- Geografia
('2024-11-11', 420.00, 'Vespertino', 2, 11),  -- Ciências
('2024-11-12', 380.00, 'Noturno', 6, 12),  -- Educação Física
('2024-11-13', 500.00, 'Matutino', 2, 13),  -- Matemática
('2024-11-14', 450.00, 'Vespertino', 2, 14),  -- Português
('2024-11-15', 400.00, 'Noturno', 1, 15),  -- História
('2024-11-16', 450.00, 'Matutino', 3, 16),  -- Geografia
('2024-11-17', 420.00, 'Vespertino', 2, 17),  -- Ciências
('2024-11-18', 380.00, 'Noturno', 6, 18),  -- Educação Física
('2024-11-19', 500.00, 'Matutino', 2, 19),  -- Matemática
('2024-11-20', 450.00, 'Vespertino', 2, 20),  -- Português
('2024-11-21', 400.00, 'Noturno', 1, 21),  -- História
('2024-11-22', 450.00, 'Matutino', 3, 22),  -- Geografia
('2024-11-23', 420.00, 'Vespertino', 2, 23),  -- Ciências
('2024-11-24', 380.00, 'Noturno', 6, 24),  -- Educação Física
('2024-11-25', 500.00, 'Matutino', 2, 25),  -- Matemática
('2024-11-26', 450.00, 'Vespertino', 2, 26),  -- Português
('2024-11-27', 400.00, 'Noturno', 1, 27),  -- História
('2024-11-28', 450.00, 'Matutino', 3, 28),  -- Geografia
('2024-11-29', 420.00, 'Vespertino', 2, 29),  -- Ciências
('2024-11-30', 380.00, 'Noturno', 6, 30);  -- Educação Física