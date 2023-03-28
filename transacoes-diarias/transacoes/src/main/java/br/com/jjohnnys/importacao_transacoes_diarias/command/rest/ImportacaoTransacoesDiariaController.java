package br.com.jjohnnys.importacao_transacoes_diarias.command.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jjohnnys.importacao_transacoes_diarias.command.events.ImportacaoTransacoesDiariaCommand;
import br.com.jjohnnys.importacao_transacoes_diarias.cqs.Bus;

@RestController
@RequestMapping("/importacao_transacao_diaria")
public class ImportacaoTransacoesDiariaController {

    @Autowired
    private Bus bus;

    @PostMapping
    public ResponseEntity<String> novaTransacao(@RequestBody @Valid ImportacaoTransacoesDiariaCommand command) {        
        return ResponseEntity.ok(bus.executeCommand(command));      
    }
    
}
