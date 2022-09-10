package br.com.jjohnnys.transacoesDiarias.bancaria.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class TransacaoBancariaCreateEvent {

    @TargetAggregateIdentifier
    private String idtransacao;
    private String bancoOrigem;
    private String agenciaOrigem;
    private String contaOrigem; 
    private String bancoDestino;
    private String agenciaDestino;
    private String contaDestino;
    private LocalDate data; 
    private BigDecimal valor;
    
}
