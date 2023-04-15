package br.com.jjohnnys.importacao_transacoes_diarias.store;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "transacoes_altos_valores")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoComAltoValorEntity {

    @Id
    private String bancoOrigem;
    private String agenciaOrigem;
    private String contaOrigem;
    private String bancoDestino;
    private String agenciaDestino;
    private String contaDestino;
    private LocalDate data;    
    private BigDecimal valor;    
}
