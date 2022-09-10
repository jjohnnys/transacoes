package br.com.jjohnnys.transacoesDiarias.bancaria.store;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.jjohnnys.transacoesDiarias.bancaria.command.TransacaoBancariaCreateEvent;
import br.com.jjohnnys.transacoesDiarias.shared.enums.TipoMovimentacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "conta_transacao_bancaria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaTransacaoBancariaEntity {
    
    @Id
    private String idtransacao;
    private String banco;
    private String agencia;
    private String conta;
    private String tipoMovimentacao; 
    private LocalDate dataTransacao; 
    private LocalDateTime dataCriacao;
    private BigDecimal valor;


    public static ContaTransacaoBancariaEntity criarContaTransacaoBancariaOrigemEntity(TransacaoBancariaCreateEvent event) {

        ContaTransacaoBancariaEntity contaTransacaoBancariaEntity = 
            new ContaTransacaoBancariaEntity(event.getIdtransacao(), 
                event.getBancoOrigem(), 
                event.getAgenciaOrigem(), 
                event.getContaOrigem(),
                TipoMovimentacaoEnum.ENTRADA.getTipo(),
                event.getData(), 
                LocalDateTime.now(), 
                event.getValor());

        return contaTransacaoBancariaEntity;        

    }

    public static ContaTransacaoBancariaEntity criarContaTransacaoBancariaDestinoEntity(TransacaoBancariaCreateEvent event) {

        ContaTransacaoBancariaEntity contaTransacaoBancariaEntity = 
            new ContaTransacaoBancariaEntity(event.getIdtransacao(), 
                event.getBancoDestino(), 
                event.getAgenciaDestino(), 
                event.getContaDestino(),
                TipoMovimentacaoEnum.SAIDA.getTipo(),
                event.getData(), 
                LocalDateTime.now(), 
                event.getValor());

        return contaTransacaoBancariaEntity;        

    }


    public static List<ContaTransacaoBancariaEntity> criarContasTransacoesBancariasEntity(List<TransacaoBancariaCreateEvent> event) {
        
        List<ContaTransacaoBancariaEntity> contasTransacoesBancariasEntity = new ArrayList<ContaTransacaoBancariaEntity>();

        event.forEach(evento -> {
              contasTransacoesBancariasEntity.add(ContaTransacaoBancariaEntity.criarContaTransacaoBancariaOrigemEntity(evento));
              contasTransacoesBancariasEntity.add(ContaTransacaoBancariaEntity.criarContaTransacaoBancariaDestinoEntity(evento));

        });
        
        return contasTransacoesBancariasEntity;
    }


}
