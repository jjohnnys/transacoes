package br.com.jjohnnys.importacao_transacoes_diarias.store;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "conta_transacao_diaria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaTransacaoDiariaEntity {
    
    @Id
    private String idtransacao;
    private String banco;
    private String agencia;
    private String conta;
    private String tipoMovimentacao; 
    private LocalDate dataTransacao; 
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal valor;    
    private String importacao;


}
