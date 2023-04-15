package br.com.jjohnnys.importacao_transacoes_diarias.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.AgenciasSuspeitasRestModel;
import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.ContasSuspeitasRestModel;
import br.com.jjohnnys.importacao_transacoes_diarias.query.rest.TransacaoComAltosValoresRestModel;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.ContaTransacaoDiariaRepository;
import br.com.jjohnnys.importacao_transacoes_diarias.store.TransacaoComAltoValorEntity;
import br.com.jjohnnys.importacao_transacoes_diarias.store.TransacaoComAltosValoresRepository;

@Component
public class RelatorioMensalTransacoesQueriesHandler {

    @Autowired
    private ContaTransacaoDiariaRepository contaTransacaoDiariaRepository;
    @Autowired
    private TransacaoComAltosValoresRepository transacaoComValoresAltosRepository;

    

    @QueryHandler
    public List<ContasSuspeitasRestModel> buscarContasSuspeitas(ContasSuspeitasQuery mes) {

        AggregationResults<Map> result = contaTransacaoDiariaRepository.buscarContasSuspeitas(mes.mes);

        return result.getMappedResults().stream().map(it ->  
                buildContasSuspeitasRestModel(it.get("_id").toString())).collect(Collectors.toList());
    }

    @QueryHandler
    public List<AgenciasSuspeitasRestModel> buscarAgenciasSuspeitas(AgenciasSuspeitasQuery mes) {

        AggregationResults<Map> result = contaTransacaoDiariaRepository.buscarAgenciasSuspeitas(mes.mes);

        return result.getMappedResults().stream().map(it ->  
                buildAgenciasSuspeitasRestModel(it.get("_id").toString())).collect(Collectors.toList());
    }

    @QueryHandler
    public List<TransacaoComAltosValoresRestModel> transacoesComValoresAltos(TransacaoComAltosValoresQuery mes) {

        List<TransacaoComAltoValorEntity> results = transacaoComValoresAltosRepository.buscarTransacoesComValoresAltos(mes.mes);        

        return results.stream().map(it ->  
               TransacaoComAltosValoresRestModel.builder().
               bancoOrigem(it.getBancoOrigem()).
               agenciaOrigem(it.getAgenciaOrigem()).
               contaOrigem(it.getContaOrigem()).
               bancoDestino(it.getAgenciaDestino()).
               agenciaDestino(it.getAgenciaDestino()).
               contaDestino(it.getContaDestino()).build()
               ).collect(Collectors.toList());
    }



    

    private ContasSuspeitasRestModel buildContasSuspeitasRestModel(String contaSuspeita) {

        StringTokenizer st = new StringTokenizer(contaSuspeita, ",");
        String banco = st.nextToken().replace("{banco=", "");
        String agencia = st.nextToken().replace("agencia=", "");
        String conta = st.nextToken().replace("conta=", "");
        String total = st.nextToken().replace("total=", "").replace("}", "");
        
        return  new ContasSuspeitasRestModel(banco, agencia, conta, total);
    }

    private AgenciasSuspeitasRestModel buildAgenciasSuspeitasRestModel(String contaSuspeita) {

        StringTokenizer st = new StringTokenizer(contaSuspeita, ",");
        String banco = st.nextToken().replace("{banco=", "");
        String agencia = st.nextToken().replace("agencia=", "");
        String total = st.nextToken().replace("total=", "").replace("}", "");
        
        return  new AgenciasSuspeitasRestModel(banco, agencia, total);
    }

   
    
}
