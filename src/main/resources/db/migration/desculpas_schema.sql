-- ============================================================
-- QUERIES - PROJETO TB_DESCULPAS (MySQL)
-- Adaptado para MySQL 8.0+
-- ============================================================

-- ============================================================
-- 1. d_contexto_entrada
-- ============================================================
CREATE TABLE IF NOT EXISTS d_contexto_entrada (
    id_contexto   INT          PRIMARY KEY,
    contexto      VARCHAR(100) NOT NULL,
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO d_contexto_entrada (id_contexto, contexto) VALUES
(1, 'cancelar rolê'),
(2, 'sair de encontro'),
(3, 'fugir de ligação'),
(4, 'recusar convite'),
(5, 'pedir resgate social'),
(6, 'encerrar conversa');


-- ============================================================
-- 2. d_tipo_desculpa
-- ============================================================
CREATE TABLE IF NOT EXISTS d_tipo_desculpa (
    id_tipo        INT          PRIMARY KEY,
    tipo_desculpa  VARCHAR(100) NOT NULL,
    trecho         VARCHAR(200),
    created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

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


-- ============================================================
-- 3. d_situacao
-- ============================================================
CREATE TABLE IF NOT EXISTS d_situacao (
    id_situacao  INT          PRIMARY KEY,
    situacao     VARCHAR(100) NOT NULL,
    trecho       VARCHAR(200),
    created_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

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


-- ============================================================
-- 4. d_intimidade
-- ============================================================
CREATE TABLE IF NOT EXISTS d_intimidade (
    id_intimidade  INT          PRIMARY KEY,
    intimidade     VARCHAR(100) NOT NULL,
    trecho         VARCHAR(200),
    created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

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


-- ============================================================
-- 5. d_nivel_drama
-- ============================================================
CREATE TABLE IF NOT EXISTS d_nivel_drama (
    id_nivel     INT          PRIMARY KEY,
    nivel_drama  VARCHAR(100) NOT NULL,
    trecho       VARCHAR(200),
    created_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO d_nivel_drama (id_nivel, nivel_drama, trecho) VALUES
(1, 'baixo',          'Tive um previsto'),
(2, 'médio',          'Algo desagradavel aconteceu'),
(3, 'alto',           'Tive uma emergencia'),
(4, 'cinematográfico','Um grande desastre aconteceu'),
(5, 'apocalíptico',   'Algo que eu mal consigo explicar aconteceu');


-- ============================================================
-- 6. d_tom
-- ============================================================
CREATE TABLE IF NOT EXISTS d_tom (
    id_tom  INT          PRIMARY KEY,
    tom     VARCHAR(100) NOT NULL,
    trecho  VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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


-- ============================================================
-- 7. d_tema
-- ============================================================
CREATE TABLE IF NOT EXISTS d_tema (
    id_tema  INT          PRIMARY KEY,
    tema     VARCHAR(100) NOT NULL,
    trecho   VARCHAR(300),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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


-- ============================================================
-- 8. d_locais
-- ============================================================
CREATE TABLE IF NOT EXISTS d_locais (
    id_local  INT          PRIMARY KEY,
    local     VARCHAR(100) NOT NULL,
    trecho    VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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


-- ============================================================
-- 9. d_chamadas_notificacao
-- ============================================================
CREATE TABLE IF NOT EXISTS d_chamadas_notificacao (
    id_suporte  INT          PRIMARY KEY,
    suporte     VARCHAR(200) NOT NULL,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

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
-- 10. d_situacao_gen (tabela gerada por IA — estrutura)
-- ============================================================
CREATE TABLE IF NOT EXISTS d_situacao_gen (
    id_situacao_gen  INT         AUTO_INCREMENT PRIMARY KEY,
    id_situacao      INT         NOT NULL,
    situacao_texto   TEXT        NOT NULL,
    created_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_situacao) REFERENCES d_situacao(id_situacao)
);

-- Exemplo dos primeiros registros gerados
INSERT IGNORE INTO d_situacao_gen (id_situacao, situacao_texto, created_at) VALUES
(1, 'decretou estado de emergência e exigiu um mínimo de 30 minutos de silêncio absoluto', '2026-05-31 16:30:06'),
(1, 'solicitou férias compulsórias após um evento social de mais de 1 hora', '2026-05-31 16:30:06'),
(1, 'ameaçou iniciar uma greve geral se não receber um vale-streaming para o fim de semana', '2026-05-31 16:30:06'),
(1, 'fugiu para o mundo paralelo das recomendações de filmes do streaming que me julga', '2026-05-31 16:30:06'),
(1, 'abriu um processo de burnout por excesso de interação em menos de 45 minutos', '2026-05-31 16:30:06');


-- ============================================================
-- FIM DO SCRIPT
-- ============================================================
