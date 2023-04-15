package br.com.jjohnnys.importacao_transacoes_diarias.command.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.importacao_transacoes_diarias.command.core.TransacaoDiaria;
import br.com.jjohnnys.importacao_transacoes_diarias.query.TransacaoComAltosValoresQuery;
import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.TransacaoComAltosValoresRestModel;
import br.com.jjohnnys.importacao_transacoes_diarias.shared.Utils.DateUtil;
import br.com.jjohnnys.importacao_transacoes_diarias.shared.enums.TipoMovimentacaoEnum;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaRepository;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ImportacaoEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ImportacaoEntityRepository;
import br.com.jjohnnys.importacao_transacoes_diarias.store.TransacaoComAltoValorEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.TransacaoComAltosValoresRepository;

@Component
public class ImportacaoTransacoesDiariaLoockupEventsHandler {

    @Autowired
    private ContaTransacaoDiariaRepository contaTransacaoBancariaRepository;

    @Autowired
    private ImportacaoEntityRepository importacaoRepository;

    @Autowired
    private TransacaoComAltosValoresRepository transacaoComAltosValoresRepository;

    @Autowired
    private QueryUpdateEmitter queryUpdateEmitter;
    


    @EventHandler
    public void on(ImportacaoTransacoesDiariaCreateEvent event) {

        event.validaTransacoes();
        salvaImportacao(event);
        salvarContasTransacoesDiariaOrigem(event);
        salvarContasTransacoesDiariaDestino(event);
        salvarTransacoesComAltosValores(event);       

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

    private void salvarTransacoesComAltosValores(ImportacaoTransacoesDiariaCreateEvent event) {
        if(!event.retornaTransacoesComAltosValores().isEmpty()) {
            List<TransacaoComAltoValorEntity> transacoesComALtosValores = getTransacoesComAltosValoresEntity(event.retornaTransacoesComAltosValores());
            transacaoComAltosValoresRepository.saveAll(transacoesComALtosValores);
            transacoesComALtosValores.forEach(transacaoComAltoValor -> {
                queryUpdateEmitter.emit(TransacaoComAltosValoresQuery.class, query -> true, transacaoComAltoValor);   
            });            
        }
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
            transacaoDiaria.getValor(),
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
            transacaoDiaria.getValor(),
            event.getIdImportacao());

            transacoeDiariaEntities.add(contaTransacaoBancariaEntity);
        });       

        return transacoeDiariaEntities;        

    }

    private void salvarContasTransacoesDiaria(List<ContaTransacaoDiariaEntity> contasTransacoesDiarias) {
        contaTransacaoBancariaRepository.saveAll(contasTransacoesDiarias);
    }

    private List<TransacaoComAltoValorEntity> getTransacoesComAltosValoresEntity(List<TransacaoDiaria> transacoesDiaria) {
        
        return transacoesDiaria.stream().map(transacaoDiaria -> 
            new TransacaoComAltoValorEntity(
                transacaoDiaria.getBancoOrigem(), 
                transacaoDiaria.getAgenciaOrigem(), 
                transacaoDiaria.getContaOrigem(), 
                transacaoDiaria.getBancoDestino(),
                transacaoDiaria.getAgenciaDestino(),
                transacaoDiaria.getBancoDestino(), 
                DateUtil.stringToLocalDate(transacaoDiaria.getData()),
                transacaoDiaria.getValor())).collect(Collectors.toList()); 

    }

}
