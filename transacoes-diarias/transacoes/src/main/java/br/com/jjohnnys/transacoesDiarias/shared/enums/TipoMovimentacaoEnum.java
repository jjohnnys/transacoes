package br.com.jjohnnys.transacoesDiarias.shared.enums;

public enum TipoMovimentacaoEnum {

    ENTRADA("Entrada"),
    SAIDA("Saída");

    private String tipo;

    private TipoMovimentacaoEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }  

}
