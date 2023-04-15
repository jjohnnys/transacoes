package br.com.jjohnnys.importacao_transacoes_diarias.query.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jjohnnys.importacao_transacoes_diarias.cqs.Bus;
import br.com.jjohnnys.importacao_transacoes_diarias.query.AgenciasSuspeitasQuery;
import br.com.jjohnnys.importacao_transacoes_diarias.query.ContasSuspeitasQuery;
import br.com.jjohnnys.importacao_transacoes_diarias.query.TransacaoComAltosValoresQuery;

@RestController
@RequestMapping("/relatorio_movimentacoes")
public class RelatorioMensalMovimentacoesController {

    @Autowired
    private Bus bus;

    


    @GetMapping("/contas_suspeitas{mes}")
    public ResponseEntity<List<RestModel>> contasSuspeitas(@RequestParam(required = true) int mes) {        
        return ResponseEntity.ok(bus.executeQuery(new ContasSuspeitasQuery(mes)));
    }

    @GetMapping("/agencias_suspeitas{mes}")
    public ResponseEntity<List<RestModel>> agenciasSuspeitas(@RequestParam(required = true) int mes) {
        return ResponseEntity.ok(bus.executeQuery(new AgenciasSuspeitasQuery(mes)));
    }

    @GetMapping("/transacoes_valores_altos{mes}")
    public ResponseEntity<List<RestModel>> transacoesComValoresAltos(@RequestParam(required = true) int mes) {
        return ResponseEntity.ok(bus.executeQuery(new TransacaoComAltosValoresQuery(mes)));
    }
    


}
