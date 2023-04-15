package br.com.jjohnnys.importacao_transacoes_diarias.command.core;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransacaoDiaria {

    @NotBlank(message = "Banco Origem é obrigatório")
    private String bancoOrigem;

    @NotBlank(message = "Agência Origem é obrigatório")
    private String agenciaOrigem;

    @NotBlank(message = "Conta Origem é obrigatório")
    private String contaOrigem;

    @NotBlank(message = "Banco Destino é obrigatório")
    private String bancoDestino;

    @NotBlank(message = "Agência Destino é obrigatório")
    private String agenciaDestino;

    @NotBlank(message = "Conta Destino é obrigatório")
    private String contaDestino;

    @NotBlank(message = "Data da transação é obrigatório")
    private String data;
    
    @NotBlank(message = "Valor da transação é obrigatório")
    private BigDecimal valor;

}
