package br.com.jjohnnys.importacao_transacoes_diarias.store;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "importacao")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImportacaoEntity {

    @Id
    private String idImportacao;
    private String arquivo;
    private String usuario;
    private LocalDate dataInportacao;
    
}
