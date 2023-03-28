package br.com.jjohnnys.importacao_transacoes_diarias.cqs;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.ContasSuspeitasRestModel;
import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.RestModel;
import lombok.AllArgsConstructor;

@Component
public class Registry implements Bus {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    private Class<? extends RestModel> clazz;

    public <C> String executeCommand(C command) {
        return commandGateway.sendAndWait(command);
    }

    @Override
    public <Q>  List<RestModel> executeQuery(Q query) {   
        
        List<RestModel> restModel = new ArrayList<>();
        restModel = queryGateway.query(query, 
            ResponseTypes.multipleInstancesOf(RestModel.class)).join();

            return restModel;
    }
    
}
