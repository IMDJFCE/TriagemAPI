CREATE TABLE convite (
    created_at timestamp(6),
    data_envio timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    convite_id varchar(255) not null,
    status varchar(255) not null check (status in ('PENDENTE','ENVIADO')),
    primary key (convite_id)
);

CREATE TABLE habilidade (
    peso varchar(2),
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    habilidade varchar(50),
    habilidade_id varchar(255) not null,
    primary key (habilidade_id)
);

CREATE TABLE oportunidade (
    data_final date not null,
    data_inicial date not null,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    email varchar(70),
    titulo varchar(100),
    descricao varchar(255),
    habilidades varchar(255),
    informacoes varchar(255),
    oportunidade_id varchar(255) not null,
    triagem varchar(255),
    primary key (oportunidade_id)
);

CREATE TABLE perfil (
    is_deficiente boolean not null,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    genero varchar(255) not null check (genero in ('MASCULINO','FEMININO')),
    habilidades varchar(255),
    perfil_id varchar(255) not null,
    raca varchar(255) not null check (raca in ('AMARELO','INDIGENA','BRANCO','PARDO','PRETO')),
    primary key (perfil_id)
);

CREATE TABLE triagem (
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    tipo varchar(50),
    triagem_id varchar(255) not null,
    primary key (triagem_id)
);

CREATE TABLE usuario (
    data_nascimento date not null,
    created_at timestamp(6),
    deleted_at timestamp(6),
    updated_at timestamp(6),
    tipo varchar(15),
    matricula varchar(50),
    senha varchar(50),
    email varchar(70),
    nome varchar(100),
    perfil_id varchar(255) unique,
    usuario_id varchar(255) not null,
    primary key (usuario_id)
);

ALTER TABLE usuario
ADD CONSTRAINT FK9po12ytp6krwvwht1kmd0qgxf
FOREIGN KEY (perfil_id)
REFERENCES perfil(perfil_id);