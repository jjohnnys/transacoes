package br.com.jjohnnys.importacao_transacoes_diarias.query.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgenciasSuspeitasRestModel implements RestModel {

    private String banco;
    private String agencia;
    private String valorTotal;

}
