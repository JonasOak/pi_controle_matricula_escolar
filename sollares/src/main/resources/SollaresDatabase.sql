-- Criando o banco de dados
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
    id_professor INT,  -- Chave estrangeira para tb_pessoa
    PRIMARY KEY (codigo),
    FOREIGN KEY (id_professor) REFERENCES tb_pessoa(id_pessoa)
);

-- Criação da tabela tb_matricula
CREATE TABLE tb_matricula (
    id_mat INT NOT NULL AUTO_INCREMENT,
    data_matricula DATE,
    valor_pago DECIMAL(10, 2),
    periodo VARCHAR(10),
    id_disciplina INT,  -- Chave estrangeira para tb_disciplina
    id_aluno INT,       -- Chave estrangeira para tb_pessoa
    PRIMARY KEY (id_mat),
    FOREIGN KEY (id_disciplina) REFERENCES tb_disciplina(codigo),
    FOREIGN KEY (id_aluno) REFERENCES tb_pessoa(id_pessoa)
);

-- Criação da tabela tb_usuario
CREATE TABLE tb_usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    cargo VARCHAR(100),
    login VARCHAR(50),
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
('Gertrudes', 'Avenida das Palmeiras, 202', 'BA', '(71) 97654-3210', '567.890.123-44', 'gertrudes@gmail.com');