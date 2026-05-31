-- ============================================================
-- SCRIPT DE INICIALIZAÇÃO - DESCULPAAI
-- Executado automaticamente na primeira implantação
-- ============================================================

-- Criar banco de dados se não existir
CREATE DATABASE IF NOT EXISTS desculpaai DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE desculpaai;

-- ============================================================
-- TABELAS DIMENSIONAIS
-- ============================================================

CREATE TABLE IF NOT EXISTS d_contexto_entrada (
    id_contexto   INT          PRIMARY KEY,
    contexto      VARCHAR(100) NOT NULL,
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_tipo_desculpa (
    id_tipo        INT          PRIMARY KEY,
    tipo_desculpa  VARCHAR(100) NOT NULL,
    trecho         VARCHAR(200),
    created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_situacao (
    id_situacao  INT          PRIMARY KEY,
    situacao     VARCHAR(100) NOT NULL,
    trecho       VARCHAR(200),
    created_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_intimidade (
    id_intimidade  INT          PRIMARY KEY,
    intimidade     VARCHAR(100) NOT NULL,
    trecho         VARCHAR(200),
    created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_nivel_drama (
    id_nivel     INT          PRIMARY KEY,
    nivel_drama  VARCHAR(100) NOT NULL,
    trecho       VARCHAR(200),
    created_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_tom (
    id_tom  INT          PRIMARY KEY,
    tom     VARCHAR(100) NOT NULL,
    trecho  VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_tema (
    id_tema  INT          PRIMARY KEY,
    tema     VARCHAR(100) NOT NULL,
    trecho   VARCHAR(300),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_locais (
    id_local  INT          PRIMARY KEY,
    local     VARCHAR(100) NOT NULL,
    trecho    VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_chamadas_notificacao (
    id_suporte  INT          PRIMARY KEY,
    suporte     VARCHAR(200) NOT NULL,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS d_situacao_gen (
    id_situacao_gen  INT         AUTO_INCREMENT PRIMARY KEY,
    id_situacao      INT         NOT NULL,
    situacao_texto   TEXT        NOT NULL,
    created_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_situacao) REFERENCES d_situacao(id_situacao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- ÍNDICES PARA PERFORMANCE
-- ============================================================

CREATE INDEX idx_d_situacao_gen_id_situacao ON d_situacao_gen(id_situacao);
CREATE INDEX idx_d_situacao_gen_created_at ON d_situacao_gen(created_at);

-- ============================================================
-- INSERIR DADOS DIMENSIONAIS
-- ============================================================

INSERT IGNORE INTO d_contexto_entrada (id_contexto, contexto) VALUES
(1, 'cancelar rolê'),
(2, 'sair de encontro'),
(3, 'fugir de ligação'),
(4, 'recusar convite'),
(5, 'pedir resgate social'),
(6, 'encerrar conversa');

INSERT IGNORE INTO d_tipo_desculpa (id_tipo, tipo_desculpa, trecho) VALUES
(1,  'cancelar rolê',          'vou precisar cancelar'),
(2,  'sair de encontro',       'preciso ir'),
(3,  'fugir de ligação',       'não posso falar'),
(4,  'recusar convite',        'vou recusar'),
(5,  'encerrar conversa',      'preciso encerrar'),
(6,  'indisponibilidade',      'vou ficar indisponível'),
(7,  'inventar emergência leve','aconteceu algo'),
(8,  'remarcar compromisso',   'vamos remarcar'),
(9,  'evitar contato futuro',  'preciso me afastar'),
(10, 'recusar elegante',       'vou precisar recusar');

INSERT IGNORE INTO d_situacao (id_situacao, situacao, trecho) VALUES
(1,  'enlouqueceu',                          NULL),
(2,  'cansou',                               NULL),
(3,  'ficou abalado',                        NULL),
(4,  'fugiu',                                NULL),
(5,  'se perdeu',                            NULL),
(6,  'foi convocado',                        NULL),
(7,  'está indisponível',                    NULL),
(8,  'entrou em staff',                      NULL),
(9,  'esqueceu completamente',               NULL),
(10, 'foi sequestrado pela rotina',          NULL),
(11, 'está sem bateria social',              NULL),
(12, 'entrou em crise existencial',          NULL),
(13, 'foi arrastado para outra obrigação',   NULL),
(14, 'está resolvendo um problema inesperado', NULL),
(15, 'está em recuperação emocional',        NULL);

INSERT IGNORE INTO d_intimidade (id_intimidade, intimidade, trecho) VALUES
(1,  'desconhecido',         'nome da pessoa'),
(2,  'conhecido',            'Querido'),
(3,  'colega de trabalho',   'Parceiro'),
(4,  'amigo',                'Meu bem'),
(5,  'melhor amiga',         'Minha vida'),
(6,  'crush',                'Vida'),
(7,  'família',              'nome do parente'),
(8,  'chefe',                'Chefe'),
(9,  'namorado',             'Amor'),
(10, 'cliente',              'nome do cliente');

INSERT IGNORE INTO d_nivel_drama (id_nivel, nivel_drama, trecho) VALUES
(1, 'baixo',          'Tive um previsto'),
(2, 'médio',          'Algo desagradavel aconteceu'),
(3, 'alto',           'Tive uma emergencia'),
(4, 'cinematográfico','Um grande desastre aconteceu'),
(5, 'apocalíptico',   'Algo que eu mal consigo explicar aconteceu');

INSERT IGNORE INTO d_tom (id_tom, tom, trecho) VALUES
(1,  'engraçado',       'Eu também não esperava por essa'),
(2,  'fofo emocional',  'Espero que entenda'),
(3,  'educado',         'Me desculpe'),
(4,  'seco',            'É isso'),
(5,  'dramático',       'Sinto muitissimo'),
(6,  'misterioso',      'Depois te explico melhor'),
(7,  'caótico',         'Nem eu entendi direito'),
(8,  'corporativo',     'Agradeço a compreensão'),
(9,  'emocional',       'Me sinto muito mal por isso'),
(10, 'debochado',       'Sabe como é né?');

INSERT IGNORE INTO d_tema (id_tema, tema, trecho) VALUES
(1,  'animais',                'Meu gato'),
(2,  'objetos',                'Minha maquina de lavar'),
(3,  'setores de consumo',     'Promoção para compradores compulsivos'),
(4,  'eventos',                'Evento para ansiosos do mês que vem'),
(5,  'compromissos judiciais', 'Serei testemunha ocular'),
(6,  'família',                NULL),
(7,  'tecnologia',             NULL),
(8,  'trabalho',               NULL),
(9,  'saúde',                  NULL),
(10, 'transporte',             NULL),
(11, 'casa',                   NULL),
(12, 'vizinhança',             NULL),
(13, 'burocracia',             NULL),
(14, 'educação',               NULL),
(15, 'viagens',                NULL),
(16, 'hobbies',                NULL),
(17, 'esportes',               NULL),
(18, 'relacionamentos',        NULL),
(19, 'compras',                NULL),
(20, 'serviços públicos',      NULL);

INSERT IGNORE INTO d_locais (id_local, local, trecho) VALUES
(1,  'date/encontro',            'acho que ainda estou pronta'),
(2,  'barzinho',                 'esse lugar'),
(3,  'festa',                    'muitas pessoas'),
(4,  'happy hour trabalho',      'amanha preciso acordar cedo'),
(5,  'reunião familiar',         'queridos, preciso sair'),
(6,  'ligação inesperada',       'estou ocupada'),
(7,  'conversa desconfortável',  'se quiser falar depois'),
(8,  'evento social',            'tenho outro compromisso'),
(9,  'convite de última hora',   'se tivesse falado antes'),
(10, 'rolê aleatório',           'não tem como confirmar'),
(11, 'casamento',                NULL),
(12, 'academia',                 NULL),
(13, 'shopping',                 NULL),
(14, 'show',                     NULL),
(15, 'restaurante',              NULL),
(16, 'viagem',                   NULL),
(17, 'evento corporativo',       NULL),
(18, 'aula/curso',               NULL),
(19, 'casa de amigos',           NULL),
(20, 'churrasco',                NULL);

INSERT IGNORE INTO d_chamadas_notificacao (id_suporte, suporte) VALUES
(1,  'me liga'),
(2,  'preciso de você agora'),
(3,  'consegue vir aqui'),
(4,  'precisamos da sua ajuda'),
(5,  'estamos te esperando'),
(6,  'venha para ca'),
(7,  'precisamos de você'),
(8,  'venha agora'),
(9,  'estamos te esperando'),
(10, 'corre'),
(11, 'vem rapido por favor'),
(12, 'para tudo que estiver fazendo'),
(13, 'vem para ca imediatamente'),
(14, 'venha aqui, depois explico'),
(15, 'estão todos te esperando');

-- ============================================================
-- FIM DO SCRIPT DE INICIALIZAÇÃO
-- ============================================================
