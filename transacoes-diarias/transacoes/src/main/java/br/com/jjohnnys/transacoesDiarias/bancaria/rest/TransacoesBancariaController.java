package br.com.jjohnnys.transacoesDiarias.bancaria.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jjohnnys.transacoesDiarias.bancaria.command.CadastrarTransacaoBancaria;

@RestController
@RequestMapping("/transacao_bancaria")
public class TransacoesBancariaController {

    @Autowired
    private CadastrarTransacaoBancaria cadastrarTransacaoBancaria;


    @PostMapping("/transacao")
    public String novaTransacao(@RequestBody @Valid TransacaoBancariaDTO transacaoBancariaDTO) {        
        return cadastrarTransacaoBancaria.execute(transacaoBancariaDTO);
    }

    @PostMapping("/transacoes")
    public String novaTransacao(@RequestBody @Valid List<TransacaoBancariaDTO> transacoesBancariasDTO) {        
        return cadastrarTransacaoBancaria.execute(transacoesBancariasDTO);
    }
    
}
