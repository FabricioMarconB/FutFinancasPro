DROP DATABASE IF EXISTS futfinancas_db;
CREATE DATABASE futfinancas_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE futfinancas_db;


CREATE TABLE usuarios (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha_hash CHAR(128) NOT NULL,
    sal VARCHAR(64) NOT NULL,     
    tipo_usuario VARCHAR(30) NOT NULL, 
    PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE clubes (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    sigla CHAR(3) NOT NULL UNIQUE,
    cnpj CHAR(18) NOT NULL UNIQUE,
    estadio VARCHAR(100) NOT NULL,
    orcamento DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE atletas (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    posicao VARCHAR(40) NOT NULL,
    numero_camisa INT UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE contratos (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    clube_id INT UNSIGNED NOT NULL,
    atleta_id INT UNSIGNED NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    salario DECIMAL(12, 2) NOT NULL,
    multa_rescisoria DECIMAL(15, 2) NOT NULL,
    status_contratual VARCHAR(30) NOT NULL, 
    PRIMARY KEY (id),
    CONSTRAINT fk_contrato_clube FOREIGN KEY (clube_id) REFERENCES clubes(id) ON DELETE CASCADE,
    CONSTRAINT fk_contrato_atleta FOREIGN KEY (atleta_id) REFERENCES atletas(id) ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE TABLE lancamentos_financeiros (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    clube_id INT UNSIGNED NOT NULL,
    tipo_lancamento VARCHAR(20) NOT NULL, 
    categoria VARCHAR(50) NOT NULL,      
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    data_lancamento DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_lancamento_clube FOREIGN KEY (clube_id) REFERENCES clubes(id) ON DELETE CASCADE
) ENGINE=InnoDB;