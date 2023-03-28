package br.com.jjohnnys.importacao_transacoes_diarias;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.jjohnnys.importacao_transacoes_diarias.command.interceptors.ImportacaoTransacaoDiariaCommandInterceptor;

@SpringBootApplication
public class TransacoesApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransacoesApplication.class, args);
	}

	@Autowired
	public void registrarCriarTransacaoBancariaCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(ImportacaoTransacaoDiariaCommandInterceptor.class));
	}

	

}
