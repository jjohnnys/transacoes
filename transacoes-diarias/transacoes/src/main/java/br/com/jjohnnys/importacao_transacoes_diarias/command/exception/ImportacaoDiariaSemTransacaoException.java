package br.com.jjohnnys.importacao_transacoes_diarias.command.exception;

public class ImportacaoDiariaSemTransacaoException extends RuntimeException {

    public ImportacaoDiariaSemTransacaoException(String idImportacao) {
        super(String.format("Importacao %s sem transacao", idImportacao));
    } 
}
