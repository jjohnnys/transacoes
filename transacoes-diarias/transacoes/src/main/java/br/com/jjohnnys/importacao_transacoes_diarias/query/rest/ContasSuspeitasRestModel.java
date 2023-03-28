package br.com.jjohnnys.importacao_transacoes_diarias.query.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContasSuspeitasRestModel implements RestModel {

    private String banco;
    private String agencia;
    private String conta;
    private String valorTotal;

}
