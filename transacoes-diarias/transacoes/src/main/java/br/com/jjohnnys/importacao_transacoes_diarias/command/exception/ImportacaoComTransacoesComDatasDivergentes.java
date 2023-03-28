package br.com.jjohnnys.importacao_transacoes_diarias.command.exception;

public class ImportacaoComTransacoesComDatasDivergentes extends RuntimeException {

    public ImportacaoComTransacoesComDatasDivergentes(String idImportacao) {
        super(String.format("A importacao %s está com transacoes com datas divergentes", idImportacao));
    }

    
    
}
