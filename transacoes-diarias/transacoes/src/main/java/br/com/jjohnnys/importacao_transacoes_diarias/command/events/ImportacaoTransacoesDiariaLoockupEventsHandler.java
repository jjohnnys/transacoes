package br.com.jjohnnys.importacao_transacoes_diarias.command.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.importacao_transacoes_diarias.shared.Utils.DateUtil;
import br.com.jjohnnys.importacao_transacoes_diarias.shared.enums.TipoMovimentacaoEnum;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaRepository;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ImportacaoEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ImportacaoEntityRepository;

@Component
public class ImportacaoTransacoesDiariaLoockupEventsHandler {

    @Autowired
    private ContaTransacaoDiariaRepository contaTransacaoBancariaRepository;
    @Autowired
    private ImportacaoEntityRepository importacaoRepository;
    


    @EventHandler
    public void on(ImportacaoTransacoesDiariaCreateEvent event) {

        event.validaDataTransacao();
        salvaImportacao(event);
        salvarContasTransacoesDiariaOrigem(event);
        salvarContasTransacoesDiariaDestino(event);

    }

    private void salvaImportacao(ImportacaoTransacoesDiariaCreateEvent event) {

        ImportacaoEntity importacaoEntity = 
            new ImportacaoEntity(event.getIdImportacao(), event.getArquivo(), event.getUsuario(), LocalDate.now());

        importacaoRepository.save(importacaoEntity);

    }

    private void salvarContasTransacoesDiariaOrigem(ImportacaoTransacoesDiariaCreateEvent event) {

        List<ContaTransacaoDiariaEntity> contaTransacaoBancariaOrigem = getContaTransacaoDiariaOrigemEntity(event);
        salvarContasTransacoesDiaria(contaTransacaoBancariaOrigem);
    }

    private void salvarContasTransacoesDiariaDestino(ImportacaoTransacoesDiariaCreateEvent event) {

        List<ContaTransacaoDiariaEntity> contaTransacaoBancariaDestino = getContaTransacaoDiariaDestinoEntity(event);
        salvarContasTransacoesDiaria(contaTransacaoBancariaDestino);
    }

    private List<ContaTransacaoDiariaEntity> getContaTransacaoDiariaOrigemEntity (ImportacaoTransacoesDiariaCreateEvent event) {

        List<ContaTransacaoDiariaEntity> transacoeDiariaEntities = new ArrayList<>();
        event.getTransacoesDiaria().forEach(transacaoDiaria -> {

            String idTransacao = UUID.randomUUID().toString();
            ContaTransacaoDiariaEntity contaTransacaoBancariaEntity = 
            new ContaTransacaoDiariaEntity(idTransacao, 
            transacaoDiaria.getBancoOrigem(), 
            transacaoDiaria.getAgenciaOrigem(), 
            transacaoDiaria.getContaOrigem(),
            TipoMovimentacaoEnum.SAIDA.getTipo(),
            DateUtil.stringToLocalDate(transacaoDiaria.getData()),
            new BigDecimal(transacaoDiaria.getValor()),
            event.getIdImportacao());

            transacoeDiariaEntities.add(contaTransacaoBancariaEntity);
        });       

        return transacoeDiariaEntities;        

    }

    private List<ContaTransacaoDiariaEntity> getContaTransacaoDiariaDestinoEntity (ImportacaoTransacoesDiariaCreateEvent event) {

        List<ContaTransacaoDiariaEntity> transacoeDiariaEntities = new ArrayList<>();
        event.getTransacoesDiaria().forEach(transacaoDiaria -> {

            String idTransacao = UUID.randomUUID().toString();
            ContaTransacaoDiariaEntity contaTransacaoBancariaEntity = 
            new ContaTransacaoDiariaEntity(idTransacao, 
            transacaoDiaria.getBancoDestino(), 
            transacaoDiaria.getAgenciaDestino(), 
            transacaoDiaria.getContaDestino(),
            TipoMovimentacaoEnum.ENTRADA.getTipo(),
            DateUtil.stringToLocalDate(transacaoDiaria.getData()), 
            new BigDecimal(transacaoDiaria.getValor()),
            event.getIdImportacao());

            transacoeDiariaEntities.add(contaTransacaoBancariaEntity);
        });       

        return transacoeDiariaEntities;        

    }

    private void salvarContasTransacoesDiaria(List<ContaTransacaoDiariaEntity> contasTransacoesDiarias) {
        contaTransacaoBancariaRepository.saveAll(contasTransacoesDiarias);
    }

}
