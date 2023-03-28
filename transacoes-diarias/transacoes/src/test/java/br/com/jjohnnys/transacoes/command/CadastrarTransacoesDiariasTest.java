package br.com.jjohnnys.transacoes.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import br.com.jjohnnys.importacao_transacoes_diarias.command.core.TransacaoDiaria;
import br.com.jjohnnys.importacao_transacoes_diarias.command.events.ImportacaoTransacoesDiariaAggregate;
import br.com.jjohnnys.importacao_transacoes_diarias.command.events.ImportacaoTransacoesDiariaCommand;
import br.com.jjohnnys.importacao_transacoes_diarias.command.events.ImportacaoTransacoesDiariaCreateEvent;
import br.com.jjohnnys.importacao_transacoes_diarias.command.exception.ImportacaoDiariaSemTransacaoException;
import br.com.jjohnnys.importacao_transacoes_diarias.command.interceptors.ImportacaoTransacaoDiariaCommandInterceptor;

public class CadastrarTransacoesDiariasTest {

   /*  private FixtureConfiguration fixture;


    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture(ImportacaoTransacoesDiariaAggregate.class);
        fixture.registerCommandDispatchInterceptor(new ImportacaoTransacaoDiariaCommandInterceptor());
    }


    //@Test
    public void cadastrarTrasacaoDiariaTest() {

        ImportacaoTransacoesDiariaCommand transacaoBancariaCommand = getImportacaoTransacoesDiariaCommand();
        fixture.given().
            when(transacaoBancariaCommand).
            expectSuccessfulHandlerExecution().
            expectEvents(getTransacaoBancariaCreateEvent(transacaoBancariaCommand));

    }

   // @Test
    public void deveLancarErroAoLancarImportacaoSemTransacoes() {
        
        assertThrows(ImportacaoDiariaSemTransacaoException.class, () ->
                fixture.givenCommands().
                    when(getImportacaoTransacoesDiariaCommandSemTransacoes()).
                    expectException(ImportacaoDiariaSemTransacaoException.class));

    }


    private ImportacaoTransacoesDiariaCommand getImportacaoTransacoesDiariaCommand() {
        
        ImportacaoTransacoesDiariaCommand command = 
            new ImportacaoTransacoesDiariaCommand("Ze lele", "arquivo.csv", criarTransacaoDiaria());
        return command;

    }

    private ImportacaoTransacoesDiariaCommand getImportacaoTransacoesDiariaCommandSemTransacoes() {
        
        ImportacaoTransacoesDiariaCommand command = 
            new ImportacaoTransacoesDiariaCommand("Chico Bento", "arquivo2.csv", new ArrayList<>());
        return command;

    }

    private ImportacaoTransacoesDiariaCreateEvent getTransacaoBancariaCreateEvent(ImportacaoTransacoesDiariaCommand transacaoBancariaCommand) {
        ImportacaoTransacoesDiariaCreateEvent transacaoBancariaCreateEvent = new ImportacaoTransacoesDiariaCreateEvent();
        BeanUtils.copyProperties(transacaoBancariaCommand, transacaoBancariaCreateEvent);
        return transacaoBancariaCreateEvent;
    }

    private List<TransacaoDiaria> criarTransacaoDiaria() {

        List<TransacaoDiaria> transacoesDiaria = new ArrayList<>(); 
            transacoesDiaria.add(new TransacaoDiaria("Itau", "0554", "825222", "City", "5554", "911144", "27-09-2022", "1200"));
            transacoesDiaria.add(new TransacaoDiaria("Bradesco", "9677", "882266", "City", "5554", "Santander", "28-09-2022", "1000"));

        return transacoesDiaria;
    } */
    
}
