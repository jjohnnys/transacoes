package br.com.jjohnnys.importacao_transacoes_diarias.command.events;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class ImportacaoTransacoesDiariaAggregate {

    @AggregateIdentifier
    private String idImportacao;
   

    @CommandHandler
    public ImportacaoTransacoesDiariaAggregate(ImportacaoTransacoesDiariaCommand transacaoBancariaCommand) {

        ImportacaoTransacoesDiariaCreateEvent transacaoBancariaCreateEvent = new ImportacaoTransacoesDiariaCreateEvent();
        idImportacao = gerarIdImportacao();
        transacaoBancariaCommand.setIdImportacao(idImportacao);
        BeanUtils.copyProperties(transacaoBancariaCommand, transacaoBancariaCreateEvent);
        AggregateLifecycle.apply(transacaoBancariaCreateEvent);

    }


    @EventSourcingHandler
    public void on(ImportacaoTransacoesDiariaCreateEvent importacaoTransacoesDiariaCreateEvent) {
        idImportacao = importacaoTransacoesDiariaCreateEvent.getIdImportacao();
        System.out.println(idImportacao);

    }

    @EventSourcingHandler
    public void on(List<ImportacaoTransacoesDiariaCreateEvent> transacoesBancariasCreateEvent) {

    }

    private String gerarIdImportacao() {
        return UUID.randomUUID().toString();
    }
    
    
}
