package br.com.senai.modulologisticasa;

<<<<<<< HEAD
=======
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
>>>>>>> feature/service
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

<<<<<<< HEAD
@SpringBootApplication
public class InitApp {
	
	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}
	
=======
import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
	@Autowired
	@Qualifier("faixaFreteServiceImpl")
	FaixaFreteService serviceFF;
	
	@Autowired
	@Qualifier("freteServiceImpl")
	FreteService serviceF;
	
>>>>>>> feature/service
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
<<<<<<< HEAD
			System.out.println("Subiu");
=======
			//FaixaFrete faixaFrete = new FaixaFrete();
			//faixaFrete.setKmMin(5);
			//faixaFrete.setKmMax(11);
			//faixaFrete.setValorKm(BigDecimal.valueOf(12.0));
			//serviceFF.inserir(faixaFrete);
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
>>>>>>> feature/service
			
		};
	}

<<<<<<< HEAD
}
=======

}
>>>>>>> feature/service
