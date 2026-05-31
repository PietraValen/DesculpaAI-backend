package com.hackathon.DesculpaAI.Data;

import java.util.Map;

import com.hackathon.DesculpaAI.Model.NivelAbsurdo;
import com.hackathon.DesculpaAI.Model.TipoAlibi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BancoAlibi {

    private Map<NivelAbsurdo, List<String>> banco = new HashMap<>();
    private Map<TipoAlibi, List<String>> bancoTipo = new HashMap<>();

    
    public BancoAlibi(Map<NivelAbsurdo, List<String>> banco) {
        this.banco = banco;
        mostrarDadosTipo();
        mostrarDadosAbsurdo();
        
        
        
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

}
