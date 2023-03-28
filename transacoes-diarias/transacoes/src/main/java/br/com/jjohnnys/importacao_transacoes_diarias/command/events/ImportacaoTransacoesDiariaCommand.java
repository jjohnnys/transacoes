package br.com.jjohnnys.importacao_transacoes_diarias.command.events;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import br.com.jjohnnys.importacao_transacoes_diarias.command.core.TransacaoDiaria;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class ImportacaoTransacoesDiariaCommand {
    
    @TargetAggregateIdentifier
    @Setter
    private String idImportacao;

    @NotBlank(message = "Usuario é obrigatorio")
    private final @NonNull String usuario;

    @NotBlank(message = "Nome do arquivo é obrigatório")
    private final @NonNull String arquivo;
    
    @NotNull(message =  "Não é possíve fazer importacao sem transacoes")
    private final @NonNull List<TransacaoDiaria> transacoesDiaria;

   

}
