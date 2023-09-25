CREATE TABLE convite (
    created_at timestamp(6),
    data_envio timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    convite_id varchar(255) NOT NULL,
    status varchar(255) NOT NULL CHECK (status IN ('PENDENTE', 'ENVIADO')),
    PRIMARY KEY (convite_id)
);

CREATE TABLE habilidade (
    peso varchar(2),
    habilidade varchar(50),
    habilidade_id varchar(255) NOT NULL,
    PRIMARY KEY (habilidade_id)
);

CREATE TABLE oportunidade (
    data_final date NOT NULL,
    data_inicial date NOT NULL,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    email varchar(70),
    titulo varchar(100),
    descricao varchar(255),
    informacoes varchar(255),
    oportunidade_id varchar(255) NOT NULL,
    triagem varchar(255),
    PRIMARY KEY (oportunidade_id)
);

CREATE TABLE oportunidade_habilidade (
    id_habilidade varchar(255) NOT NULL,
    id_oportunidade varchar(255) NOT NULL,
    PRIMARY KEY (id_habilidade, id_oportunidade)
);

CREATE TABLE perfil (
    is_deficiente boolean NOT NULL,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    genero varchar(255) NOT NULL CHECK (genero IN ('MASCULINO', 'FEMININO')),
    perfil_id varchar(255) NOT NULL,
    raca varchar(255) NOT NULL CHECK (raca IN ('AMARELO', 'INDIGENA', 'BRANCO', 'PARDO', 'PRETO')),
    PRIMARY KEY (perfil_id)
);

CREATE TABLE perfil_habilidade (
    id_habilidade varchar(255) NOT NULL,
    id_perfil varchar(255) NOT NULL,
    PRIMARY KEY (id_habilidade, id_perfil)
);

CREATE TABLE triagem (
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    tipo varchar(50),
    triagem_id varchar(255) NOT NULL,
    PRIMARY KEY (triagem_id)
);

CREATE TABLE usuario (
    data_nascimento date NOT NULL,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    tipo varchar(15),
    matricula varchar(50),
    senha varchar(50),
    email varchar(70),
    nome varchar(100),
    perfil_id varchar(255) UNIQUE,
    usuario_id varchar(255) NOT NULL,
    PRIMARY KEY (usuario_id)
);

ALTER TABLE IF EXISTS oportunidade_habilidade ADD CONSTRAINT FKisutn448yo9f64n7m2ous7bgb FOREIGN KEY (id_habilidade) REFERENCES habilidade;
ALTER TABLE IF EXISTS oportunidade_habilidade ADD CONSTRAINT FKacrlkraktctermq591lmu94ap FOREIGN KEY (id_oportunidade) REFERENCES oportunidade;
ALTER TABLE IF EXISTS perfil_habilidade ADD CONSTRAINT FK7cixglis4e212gwomc5yyklwo FOREIGN KEY (id_habilidade) REFERENCES habilidade;
ALTER TABLE IF EXISTS perfil_habilidade ADD CONSTRAINT FKpgqfijhop7qrmsjequx693rpu FOREIGN KEY (id_perfil) REFERENCES perfil;
ALTER TABLE IF EXISTS usuario ADD CONSTRAINT FK9po12ytp6krwvwht1kmd0qgxf FOREIGN KEY (perfil_id) REFERENCES perfil;