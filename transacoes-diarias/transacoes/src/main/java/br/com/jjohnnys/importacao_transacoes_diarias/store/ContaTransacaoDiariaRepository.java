package br.com.jjohnnys.importacao_transacoes_diarias.store;

import java.util.Map;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.jjohnnys.importacao_transacoes_diarias.shared.constants.ParametrosDeBusca;

@Repository
public interface ContaTransacaoDiariaRepository extends MongoRepository<ContaTransacaoDiariaEntity, String> {

    @Aggregation(pipeline = {
        "{$match: { $expr: {'$eq': [{'$month': '$dataTransacao'},?0]}}}",
        "{$group : { _id: {banco: '$banco', agencia: '$agencia', conta: '$conta', total : {$sum: '$valor'}}}}",
        "{$match: {'_id.total': { '$gt': " + ParametrosDeBusca.VALOR_CONTA_SUSPEITA  + "}}}"
    })
    public AggregationResults<Map> buscarContasSuspeitas(int mes);


    @Aggregation(pipeline = {
        "{$match: { $expr: {'$eq': [{'$month': '$dataTransacao'},?0]}}}",
        "{$group : { _id: {banco: '$banco', agencia: '$agencia', total : {$sum: '$valor'}}}}",
        "{$match: {'_id.total': { '$gt': " + ParametrosDeBusca.VALOR_AGENCIA_SUSPEITA  + "}}}"
    })
    public AggregationResults<Map> buscarAgenciasSuspeitas(int mes);

    
    
}
