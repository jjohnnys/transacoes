package br.com.jjohnnys.transacoesDiarias.bancaria.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.transacoesDiarias.bancaria.rest.TransacaoBancariaDTO;

@Component
public class CadastrarTransacaoBancaria {

    private CommandGateway commandGateway;

    public CadastrarTransacaoBancaria(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public String execute(TransacaoBancariaDTO transacaoBancariaDTO) {

        TransacaoBancariaCommand transacaoBancariaCommand = transacaoBancariaDTO.criarTransacaoBancariaCommand();
        return commandGateway.sendAndWait(transacaoBancariaCommand);
        
    }

    public String execute(List<TransacaoBancariaDTO> transacoesBancariasDTO) {

        final List<TransacaoBancariaCommand> transacoesBancariasCommand = transacoesBancariasDTO.stream().
                map(dto -> dto.criarTransacaoBancariaCommand()).collect(Collectors.toList());

        

        return null;
    }
    
}
