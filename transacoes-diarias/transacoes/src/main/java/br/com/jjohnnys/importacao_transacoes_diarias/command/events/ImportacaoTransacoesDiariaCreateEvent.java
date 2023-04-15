package br.com.jjohnnys.importacao_transacoes_diarias.command.events;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import br.com.jjohnnys.importacao_transacoes_diarias.command.core.TransacaoDiaria;
import br.com.jjohnnys.importacao_transacoes_diarias.command.exception.ImportacaoComTransacoesComDatasDivergentes;
import br.com.jjohnnys.importacao_transacoes_diarias.shared.constants.ParametrosDeBusca;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ImportacaoTransacoesDiariaCreateEvent {

    @TargetAggregateIdentifier
    private @NonNull String idImportacao;
    private @NonNull String usuario;
    private @NonNull String arquivo;
    private @NonNull List<TransacaoDiaria> transacoesDiaria = new ArrayList<>();
    private List<TransacaoDiaria> transacoesComAltosValores = new ArrayList<>();



    public void validaTransacoes() {

        validaDataTransacao();

    }

    private void validaDataTransacao() {
        String dataPrimeiraLinha = this.transacoesDiaria.get(0).getData();
        this.transacoesDiaria.forEach(transacaoDiaria -> {
             
            if(transacaoDiaria.getData().intern() != dataPrimeiraLinha.intern())
                throw new ImportacaoComTransacoesComDatasDivergentes(this.idImportacao);

            verificaTransacaoComAltoValor(transacaoDiaria);
        });
    }


    private void verificaTransacaoComAltoValor(TransacaoDiaria transacaoDiaria) {

        if(transacaoDiaria.getValor().compareTo(new BigDecimal(ParametrosDeBusca.VALOR_ALTO_TRANSACAO)) == 1);
                transacoesComAltosValores.add(transacaoDiaria);
        
    }

    public List<TransacaoDiaria> retornaTransacoesComAltosValores() {
        return Collections.unmodifiableList(transacoesComAltosValores);
    }
    
}
