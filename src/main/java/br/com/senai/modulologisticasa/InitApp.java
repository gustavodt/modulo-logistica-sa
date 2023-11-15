package br.com.senai.modulologisticasa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			//FaixaFrete faixaFrete = new FaixaFrete();
			//faixaFrete.setKmMin(18);
			//faixaFrete.setKmMax(19);
			//faixaFrete.setValorKm(BigDecimal.valueOf(6.0));
			//System.out.println(faixaFrete);
			
			//Frete frete = new Frete();
			//frete.setDataMovimento(LocalDateTime.now());
			//frete.setDistancia(BigDecimal.valueOf(8.0));
			//frete.setIdEntregador(1);
			//frete.setIdPedido(2);
			//frete.setStatus(5);
			//frete.setValorKm(BigDecimal.valueOf(6.0));
			//frete.setValorTotal(BigDecimal.valueOf(48.0));
			//frete.setTempoEntregaMinutos(10);
			//System.out.println(frete);
			
		};
	}


}
