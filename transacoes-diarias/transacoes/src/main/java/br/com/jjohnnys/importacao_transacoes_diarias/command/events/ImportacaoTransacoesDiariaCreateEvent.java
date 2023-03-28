package br.com.jjohnnys.importacao_transacoes_diarias.command.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import br.com.jjohnnys.importacao_transacoes_diarias.command.core.TransacaoDiaria;
import br.com.jjohnnys.importacao_transacoes_diarias.command.exception.ImportacaoComTransacoesComDatasDivergentes;
import br.com.jjohnnys.importacao_transacoes_diarias.shared.Utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportacaoTransacoesDiariaCreateEvent {

    @TargetAggregateIdentifier
    private String idImportacao;
    private String usuario;
    private String arquivo;
    private List<TransacaoDiaria> transacoesDiaria = new ArrayList<>();

    public void validaDataTransacao() {

        String dataPrimeiraLinha = this.transacoesDiaria.get(0).getData();
        this.transacoesDiaria.forEach(transacaoDiaria -> {
             
            if(transacaoDiaria.getData().intern() != dataPrimeiraLinha.intern())
                throw new ImportacaoComTransacoesComDatasDivergentes(this.idImportacao);
        });

    }
    
}
