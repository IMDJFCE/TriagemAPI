CREATE TABLE convite (
    convite_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    data_envio TIMESTAMP(6),
    status VARCHAR(255) NOT NULL CHECK (status IN ('PENDENTE', 'ENVIADO')),
    PRIMARY KEY (convite_id)
);

CREATE TABLE habilidade (
    habilidade_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    habilidade VARCHAR(50),
    peso VARCHAR(2),
    PRIMARY KEY (habilidade_id)
);

CREATE TABLE oportunidade (
    oportunidade_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    data_final DATE NOT NULL,
    data_inicial DATE NOT NULL,
    descricao VARCHAR(255),
    email VARCHAR(70),
    habilidades VARCHAR(255),
    informacoes VARCHAR(255),
    titulo VARCHAR(100),
    triagem VARCHAR(255),
    PRIMARY KEY (oportunidade_id)
);

CREATE TABLE perfil (
    perfil_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    genero VARCHAR(255) NOT NULL CHECK (genero IN ('MASCULINO', 'FEMININO')),
    habilidades VARCHAR(255),
    is_deficiente BOOLEAN NOT NULL,
    raca VARCHAR(255) NOT NULL CHECK (raca IN ('AMARELO', 'INDIGENA', 'BRANCO', 'PARDO', 'PRETO')),
    PRIMARY KEY (perfil_id)
);

CREATE TABLE triagem (
    triagem_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    tipo VARCHAR(50),
    PRIMARY KEY (triagem_id)
);

CREATE TABLE usuario (
    usuario_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    data_nascimento DATE NOT NULL,
    email VARCHAR(70),
    matricula VARCHAR(50),
    nome VARCHAR(100),
    senha VARCHAR(50),
    tipo VARCHAR(15),
    perfil_id VARCHAR(255),
    PRIMARY KEY (usuario_id),
    FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id)
);