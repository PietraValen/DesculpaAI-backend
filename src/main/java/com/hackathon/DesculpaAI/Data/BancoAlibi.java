package com.hackathon.DesculpaAI.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.hackathon.DesculpaAI.model.AlibiRoteiro;
import com.hackathon.DesculpaAI.model.NivelAbsurdo;
import com.hackathon.DesculpaAI.model.TipoAlibi;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * Componente que gerencia o banco de dados de alibis disponíveis.
 */
@Component
@Slf4j
public class BancoAlibi {

    private final Map<NivelAbsurdo, List<String>> banco = new HashMap<>();
    private final Map<TipoAlibi, List<String>> bancoTipo = new HashMap<>();
    private final Random random = new Random();

    /**
     * Inicializa o banco de alibis após a instanciação do bean.
     */
    @PostConstruct
    public void inicializar() {
        mostrarDadosAbsurdo();
        mostrarDadosTipo();
        log.info("Banco de Alibis inicializado com sucesso");
    }

    private void mostrarDadosAbsurdo() {
        banco.put(NivelAbsurdo.BAIXO,
                List.of("Meu papagaio começou a repetir senhas confidenciais e tive que impedir uma crise de segurança doméstica."));

        banco.put(NivelAbsurdo.MEDIO,
                List.of("Fui colocado em observação após desenvolver uma alergia extremamente rara a despertadores."));

        banco.put(NivelAbsurdo.ALTO,
                List.of("Fui convocado para impedir que a inteligência artificial da empresa assumisse o controle do sistema de café."));

        banco.put(NivelAbsurdo.CINEMATOGRAFICO,
                List.of("Descobri que um parente distante alegava ser herdeiro de um reino perdido e fui chamado para confirmar a história."));

        banco.put(NivelAbsurdo.APOCALIPTICO,
                List.of("Ganhei uma viagem misteriosa para uma ilha que não aparecia em nenhum mapa e só consegui voltar horas depois"));
    }

    private void mostrarDadosTipo() {
        bancoTipo.put(TipoAlibi.ANIMAL,
                List.of("Descobri que meu gato havia sido eleito líder honorário da família e precisei organizar a cerimônia."));

        bancoTipo.put(TipoAlibi.DOENCA,
                List.of("Descobriram que meu espirro tinha potencial para gerar energia renovável."));

        bancoTipo.put(TipoAlibi.FAMILIAR,
                List.of("Minha avó decidiu ensinar todas as receitas da família em uma única tarde."));

        bancoTipo.put(TipoAlibi.LIVRE,
                List.of("Participei de uma caça ao tesouro organizada por desconhecidos."));

        bancoTipo.put(TipoAlibi.TRABALHO,
                List.of("A empresa me enviou para negociar um acordo secreto com uma colônia em Marte."));
    }

    public Map<NivelAbsurdo, List<String>> getBanco() {
        return Collections.unmodifiableMap(banco);
    }

    public Map<TipoAlibi, List<String>> getBancoTipo() {
        return Collections.unmodifiableMap(bancoTipo);
    }

    /**
     * Obtém um álibi aleatório baseado no tipo e nível de absurdo.
     */
    public String obterAlibEAleatorio(TipoAlibi tipo, NivelAbsurdo nivel) {
        List<String> alibisPorTipo = bancoTipo.getOrDefault(tipo, List.of());
        List<String> albisPorNivel = banco.getOrDefault(nivel, List.of());

        List<String> alibisCombinados = new java.util.ArrayList<>(alibisPorTipo);
        alibisCombinados.addAll(albisPorNivel);

        if (alibisCombinados.isEmpty()) {
            log.warn("Nenhum álibi disponível para tipo: {} e nível: {}", tipo, nivel);
            return "Álibi indisponível no momento";
        }

        return alibisCombinados.get(random.nextInt(alibisCombinados.size()));
    }

    /**
     * Obtém um álibi aleatório apenas pelo tipo.
     */
    public String obterAlibPorTipo(TipoAlibi tipo) {
        List<String> alibis = bancoTipo.getOrDefault(tipo, List.of());
        if (alibis.isEmpty()) {
            log.warn("Nenhum álibi disponível para tipo: {}", tipo);
            return "Álibi indisponível no momento";
        }
        return alibis.get(random.nextInt(alibis.size()));
    }

    /**
     * Obtém um álibi aleatório apenas pelo nível de absurdo.
     */
    public String obterAlibPorNivel(NivelAbsurdo nivel) {
        List<String> alibis = banco.getOrDefault(nivel, List.of());
        if (alibis.isEmpty()) {
            log.warn("Nenhum álibi disponível para nível: {}", nivel);
            return "Álibi indisponível no momento";
        }
        return alibis.get(random.nextInt(alibis.size()));
    }

    /**
     * Obtém um roteiro completo de álibi aleatório baseado no tipo e nível de
     * absurdo.
     *
     * @param tipo tipo de álibi desejado
     * @param nivelAbsurdo nível de absurdo (1-5)
     * @return AlibiRoteiro com papel, missão, frases obrigatória e proibida
     */
    public AlibiRoteiro obterAlibiAleatorio(TipoAlibi tipo, int nivelAbsurdo) {
        NivelAbsurdo nivel = converterNivelAbsurdo(nivelAbsurdo);

        // Gera papel baseado no tipo
        String papel = gerarPapel(tipo);

        // Gera missão baseada no tipo e nível
        String missao = gerarMissao(tipo, nivel);

        // Obtém frase obrigatória
        String fraseObrigatoria = obterAlibEAleatorio(tipo, nivel);

        // Obtém frase proibida (tipo diferente para criar conflito)
        String fraseProibida = gerarFraseProibida(tipo);

        log.debug("Roteiro gerado: tipo={}, nivel={}, papel={}", tipo, nivel, papel);

        return AlibiRoteiro.builder()
                .papel(papel)
                .missao(missao)
                .fraseObrigatoria(fraseObrigatoria)
                .fraseProibida(fraseProibida)
                .build();
    }

    /**
     * Converte nível numérico (1-5) para enum NivelAbsurdo.
     */
    private NivelAbsurdo converterNivelAbsurdo(int nivel) {
        return switch (nivel) {
            case 1 ->
                NivelAbsurdo.BAIXO;
            case 2 ->
                NivelAbsurdo.MEDIO;
            case 3 ->
                NivelAbsurdo.ALTO;
            case 4 ->
                NivelAbsurdo.CINEMATOGRAFICO;
            case 5 ->
                NivelAbsurdo.APOCALIPTICO;
            default ->
                NivelAbsurdo.MEDIO;
        };
    }

    /**
     * Gera um papel baseado no tipo de álibi.
     */
    private String gerarPapel(TipoAlibi tipo) {
        return switch (tipo) {
            case TRABALHO ->
                "Executivo em Reunião Importante";
            case DOENCA ->
                "Paciente em Consulta Médica";
            case FAMILIAR ->
                "Membro da Família em Situação Crítica";
            case ANIMAL ->
                "Cuidador de Animal com Problema";
            case LIVRE ->
                "Pessoa com Compromisso Pessoal";
        };
    }

    /**
     * Gera uma missão baseada no tipo e nível de álibi.
     */
    private String gerarMissao(TipoAlibi tipo, NivelAbsurdo nivel) {
        String base = switch (tipo) {
            case TRABALHO ->
                "Convencer que estava em uma reunião de trabalho crítica";
            case DOENCA ->
                "Justificar ausência por motivos de saúde";
            case FAMILIAR ->
                "Explicar situação familiar urgente";
            case ANIMAL ->
                "Justificar problema com animal de estimação";
            case LIVRE ->
                "Ter uma desculpa genérica pronta";
        };

        String intensidade = switch (nivel) {
            case BAIXO ->
                base;
            case MEDIO ->
                base + " com detalhes convincentes";
            case ALTO ->
                base + " com histórias elaboradas";
            case CINEMATOGRAFICO ->
                base + " com narrativa criativa e coerente";
            case APOCALIPTICO ->
                base + " com a narrativa mais absurda possível";
        };

        return intensidade;
    }

    /**
     * Obtém uma frase que NÃO deve ser dita (conflita com a missão).
     */
    private String gerarFraseProibida(TipoAlibi tipo) {
        return switch (tipo) {
            case TRABALHO ->
                "Eu estava aproveitando o tempo livre";
            case DOENCA ->
                "Eu estava em uma festa";
            case FAMILIAR ->
                "Eu nem liguei";
            case ANIMAL ->
                "Eu simplesmente abandonei meu animal";
            case LIVRE ->
                "Eu esqueci";
        };
    }
}
