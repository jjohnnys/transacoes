package br.com.jjohnnys.importacao_transacoes_diarias.command.interceptors;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.importacao_transacoes_diarias.command.events.ImportacaoTransacoesDiariaCommand;
import br.com.jjohnnys.importacao_transacoes_diarias.command.exception.ImportacaoDiariaSemTransacaoException;

@Component
public class ImportacaoTransacaoDiariaCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {


    @Override    
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        
        return (index, command) -> {
            if(ImportacaoTransacoesDiariaCommand.class.equals(command.getPayloadType())) {
                ImportacaoTransacoesDiariaCommand importacaoCommand = (ImportacaoTransacoesDiariaCommand)command.getPayload();

                if(importacaoCommand.getTransacoesDiaria().isEmpty()) {
                    throw new ImportacaoDiariaSemTransacaoException(importacaoCommand.getIdImportacao());
                }    
            }
            
            return command;
        };
    }

    
}
