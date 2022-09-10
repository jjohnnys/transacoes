package br.com.jjohnnys.transacoesDiarias.bancaria.store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaTransacaoBancariaRepository extends MongoRepository<ContaTransacaoBancariaEntity, String> {

    
    
}
