package br.com.jjohnnys.importacao_transacoes_diarias.cqs;

import java.util.List;

import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.RestModel;

public interface Bus {

    public <C> String executeCommand(C command);
    public <Q> List<RestModel> executeQuery(Q query);
    
}
