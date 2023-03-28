package br.com.jjohnnys.importacao_transacoes_diarias.store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportacaoEntityRepository extends MongoRepository<ImportacaoEntity, String>{
    
}
