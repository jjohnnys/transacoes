package br.com.jjohnnys.transacoesDiarias.bancaria.command;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.transacoesDiarias.bancaria.store.ContaTransacaoBancariaEntity;
import br.com.jjohnnys.transacoesDiarias.bancaria.store.ContaTransacaoBancariaRepository;

@Component
public class TransacaoBancariaLoockupEventsHandler {

    private ContaTransacaoBancariaRepository contaTransacaoBancariaRepository;
    

    public TransacaoBancariaLoockupEventsHandler(ContaTransacaoBancariaRepository contaTransacaoBancariaRepository) {
        this.contaTransacaoBancariaRepository = contaTransacaoBancariaRepository;
    }


    @EventHandler
    public void on(TransacaoBancariaCreateEvent event) {

        ContaTransacaoBancariaEntity contaTransacaoBancariaOrigemEntity = ContaTransacaoBancariaEntity.criarContaTransacaoBancariaOrigemEntity(event);                   
        contaTransacaoBancariaRepository.save(contaTransacaoBancariaOrigemEntity);

        ContaTransacaoBancariaEntity contaTransacaoBancariaDestinoEntity = ContaTransacaoBancariaEntity.criarContaTransacaoBancariaDestinoEntity(event);                   
        contaTransacaoBancariaRepository.save(contaTransacaoBancariaDestinoEntity);

    }

    @EventHandler
    public void on(List<TransacaoBancariaCreateEvent> transacoesBancariasCreateEvent) {
        List<ContaTransacaoBancariaEntity> contasTransacoesBacariasEntity = ContaTransacaoBancariaEntity.criarContasTransacoesBancariasEntity(transacoesBancariasCreateEvent);
        contaTransacaoBancariaRepository.saveAll(contasTransacoesBacariasEntity);
    }
}
