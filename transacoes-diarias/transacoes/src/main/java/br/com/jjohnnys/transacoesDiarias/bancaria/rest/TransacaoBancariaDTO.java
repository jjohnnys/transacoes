package br.com.jjohnnys.transacoesDiarias.bancaria.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import br.com.jjohnnys.transacoesDiarias.bancaria.command.TransacaoBancariaCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TransacaoBancariaDTO {
    
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

    @NotBlank(message = "Valor da transacação é obrigatório")
    private String valor;


    public TransacaoBancariaCommand criarTransacaoBancariaCommand() {

        return TransacaoBancariaCommand.builder().
        idtransacao(UUID.randomUUID().toString()).
        bancoOrigem(getAgenciaOrigem()).
        agenciaOrigem(getAgenciaOrigem()).
        contaOrigem(getContaOrigem()).
        bancoDestino(getBancoDestino()).
        agenciaDestino(getAgenciaDestino()).
        contaDestino(getContaDestino()).
        data(convertToLocalDate(getData())).
        valor(new BigDecimal(getValor())).build();

    }

    private LocalDate convertToLocalDate(String data) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, dtf);
        return dataFormatada;
    }

}
