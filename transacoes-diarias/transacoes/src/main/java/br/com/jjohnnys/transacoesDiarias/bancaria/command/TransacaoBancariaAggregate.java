package br.com.jjohnnys.transacoesDiarias.bancaria.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class TransacaoBancariaAggregate {

    @AggregateIdentifier
    private String idTransacao;


    @CommandHandler
    public TransacaoBancariaAggregate(TransacaoBancariaCommand transacaoBancariaCommand) {

        TransacaoBancariaCreateEvent transacaoBancariaCreateEvent = new TransacaoBancariaCreateEvent();
        BeanUtils.copyProperties(transacaoBancariaCommand, transacaoBancariaCreateEvent);
        AggregateLifecycle.apply(transacaoBancariaCreateEvent);

    }

    @CommandHandler
    public TransacaoBancariaAggregate(List<TransacaoBancariaCommand> transacoesBancariasCommand) {

        List<TransacaoBancariaCreateEvent> transacoesBancariasCreateEvent = new ArrayList<TransacaoBancariaCreateEvent>();
        transacoesBancariasCommand.forEach(transacaoBancariaCommand -> {
            TransacaoBancariaCreateEvent transacaoBancariaCreateEvent = new TransacaoBancariaCreateEvent();
            BeanUtils.copyProperties(transacaoBancariaCommand, transacaoBancariaCreateEvent);
            idTransacao = UUID.randomUUID().toString();
            transacaoBancariaCreateEvent.setIdtransacao(idTransacao);
            transacoesBancariasCreateEvent.add(transacaoBancariaCreateEvent);
        });
        AggregateLifecycle.apply(transacoesBancariasCreateEvent);

    }


    @EventSourcingHandler
    public void on(TransacaoBancariaCreateEvent transacaoBancariaCreateEvent) {
        idTransacao = UUID.randomUUID().toString();
        transacaoBancariaCreateEvent.setIdtransacao(idTransacao);
        System.out.println(transacaoBancariaCreateEvent.getAgenciaOrigem());

    }

    @EventSourcingHandler
    public void on(List<TransacaoBancariaCreateEvent> transacoesBancariasCreateEvent) {

    }
    
    
}
