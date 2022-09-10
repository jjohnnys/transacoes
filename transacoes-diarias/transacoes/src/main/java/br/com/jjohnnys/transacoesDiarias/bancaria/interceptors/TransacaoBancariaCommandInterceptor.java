package br.com.jjohnnys.transacoesDiarias.bancaria.interceptors;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import br.com.jjohnnys.transacoesDiarias.bancaria.command.TransacaoBancariaCommand;

public class TransacaoBancariaCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {


    @Override    
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        
        return (index, command) -> {
            if(TransacaoBancariaCommand.class.equals(command.getPayloadType())) {
                TransacaoBancariaCommand transacaoCommand = (TransacaoBancariaCommand)command.getPayload();

                if(transacaoCommand.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Transacao sem valor");
                }    
            }
            
            return command;
        };
    }

    
}
