-- Esquema MySQL do Despesas (somente estrutura, sem dados pessoais).
-- Reconstruido a partir dos mapeamentos Hibernate/JPA e dos DAOs do projeto.
-- Uso: mysql -u usuario -p < schema.sql

CREATE DATABASE IF NOT EXISTS despesas DEFAULT CHARACTER SET utf8;
USE despesas;

-- Origens de pagamento (cartoes, contas): dia de fechamento e de vencimento
CREATE TABLE IF NOT EXISTS oriundo (
  codigo         INT NOT NULL AUTO_INCREMENT,
  descricao      VARCHAR(30),
  data_cadastro  DATE,
  dia_fechamento INT,
  dia_pag        INT,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS usuario (
  codigo  INT NOT NULL AUTO_INCREMENT,
  nome    VARCHAR(30),
  usuario VARCHAR(10),
  senha   VARCHAR(10),
  nivel   INT,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

-- Cada linha e uma parcela; cod_orindo aponta para oriundo.codigo
CREATE TABLE IF NOT EXISTS despesas (
  codigo         INT NOT NULL AUTO_INCREMENT,
  descricao      VARCHAR(50),
  data_desp      DATE,
  data_pag       DATE,
  parcelas       INT,
  parcela        INT,
  valor_parcela  FLOAT,
  valor          FLOAT,
  valor_restante FLOAT,
  status         VARCHAR(30),
  obs            VARCHAR(50),
  cod_orindo     INT NOT NULL,
  cod_usuario    INT NOT NULL,
  dat_alteracao  TIMESTAMP NULL,
  pago           INT,
  data_pagamento TIMESTAMP NULL,
  PRIMARY KEY (codigo),
  KEY fk_despesas_oriundo1 (cod_orindo),
  KEY fk_despesas_usuario1 (cod_usuario),
  CONSTRAINT fk_despesas_oriundo1 FOREIGN KEY (cod_orindo) REFERENCES oriundo (codigo),
  CONSTRAINT fk_despesas_usuario1 FOREIGN KEY (cod_usuario) REFERENCES usuario (codigo)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS forma_pagamento (
  codigo         INT NOT NULL AUTO_INCREMENT,
  nome           VARCHAR(45),
  data_cadastro  TIMESTAMP NULL,
  data_alteracao TIMESTAMP NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS despesas_pagamento (
  forma_pagamento_codigo INT NOT NULL,
  despesas_codigo        INT NOT NULL,
  data_pagamento         TIMESTAMP NULL,
  PRIMARY KEY (forma_pagamento_codigo, despesas_codigo),
  CONSTRAINT fk_forma_pagamento_has_despesas_forma_pagamento1 FOREIGN KEY (forma_pagamento_codigo) REFERENCES forma_pagamento (codigo),
  CONSTRAINT fk_forma_pagamento_has_despesas_despesas1 FOREIGN KEY (despesas_codigo) REFERENCES despesas (codigo)
) ENGINE=InnoDB;

-- Banco de contas (pode ser o mesmo banco): login do sistema e a conexao
-- do banco de despesas de cada usuario. porta_server aceita "3306/despesas".
CREATE TABLE IF NOT EXISTS usuario_server (
  codigo       INT NOT NULL AUTO_INCREMENT,
  nome         VARCHAR(45),
  user_sys     VARCHAR(15),
  senha_sys    VARCHAR(15),
  user_server  VARCHAR(20),
  senha_server VARCHAR(20),
  porta_server VARCHAR(20),
  url_server   VARCHAR(60),
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

-- Dados minimos de exemplo (ficticios). O cadastro de despesas grava cod_usuario = 1.
INSERT INTO usuario (codigo, nome, usuario, senha, nivel) VALUES (1, 'Usuario Exemplo', 'usuario', 'senha', 1);
INSERT INTO usuario_server (nome, user_sys, senha_sys, user_server, senha_server, porta_server, url_server)
VALUES ('Usuario Exemplo', 'usuario', 'senha', 'usuario', 'senha', '3306/despesas', 'localhost');
