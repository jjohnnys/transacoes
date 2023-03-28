package br.com.jjohnnys.importacao_transacoes_diarias.query;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.ContasSuspeitasQuery;
import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.ContasSuspeitasRestModel;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RelatorioMensalTransacoesHandler {

    private ContaTransacaoDiariaRepository contaTransacaoDiariaRepository;

    

    @QueryHandler
    public List<ContasSuspeitasRestModel> buscarContasSuspeitas(ContasSuspeitasQuery mes) {

        AggregationResults<Map> result = contaTransacaoDiariaRepository.buscarContasSuspeitas(mes.mes);

        return result.getMappedResults().stream().map(it ->  
                buildContasSuspeitasRestModel(it.get("_id").toString())).collect(Collectors.toList());
    }

    private ContasSuspeitasRestModel buildContasSuspeitasRestModel(String contaSuspeita) {

        StringTokenizer st = new StringTokenizer(contaSuspeita, ",");
        String banco = st.nextToken().replace("{banco=", "");
        String agencia = st.nextToken().replace("agencia=", "");
        String conta = st.nextToken().replace("conta=", "");;
        String total = st.nextToken().replace("total=", "").replace("}", "");
        
        return  new ContasSuspeitasRestModel(banco, agencia, conta, total);
    }

   
    
}
