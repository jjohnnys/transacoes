package br.com.jjohnnys.importacao_transacoes_diarias.query.rest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransacaoComAltosValoresRestModel implements RestModel {

    private String bancoOrigem;
    private String agenciaOrigem;
    private String contaOrigem;
    private String bancoDestino;
    private String agenciaDestino;
    private String contaDestino;
    private String data;    
    private String valor; 
    
}