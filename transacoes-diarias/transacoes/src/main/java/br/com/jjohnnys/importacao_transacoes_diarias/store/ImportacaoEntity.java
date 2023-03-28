package br.com.jjohnnys.importacao_transacoes_diarias.store;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Document(collection = "importacao")
@AllArgsConstructor
@Getter
public class ImportacaoEntity {

    @Id
    private String idImportacao;
    private String arquivo;
    private String usuario;
    private LocalDate dataInportacao;
    
}
