package br.com.jjohnnys.transacoesDiarias.bancaria.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoBancariaCommand {
    
    @TargetAggregateIdentifier
    private final String idtransacao;
    private final String bancoOrigem;
    private final String agenciaOrigem;
    private final String contaOrigem; 
    private final String bancoDestino;
    private final String agenciaDestino;
    private final String contaDestino;
    private final LocalDate data; 
    private final BigDecimal valor;

}
