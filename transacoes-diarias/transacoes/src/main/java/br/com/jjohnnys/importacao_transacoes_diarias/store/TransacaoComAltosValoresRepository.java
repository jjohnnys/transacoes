package br.com.jjohnnys.importacao_transacoes_diarias.store;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoComAltosValoresRepository extends MongoRepository<TransacaoComAltoValorEntity, String> {

    @Aggregation({
        "{$match: { $expr: {'$eq': [{'$month': '$dataTransacao'},?0]}}}"
    })
    public List<TransacaoComAltoValorEntity> buscarTransacoesComValoresAltos(int mes);
   
}
