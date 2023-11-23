package br.com.senai.modulologisticasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

<<<<<<< HEAD
import br.com.senai.modulologisticasa.service.proxy.CardapioMktplaceProxy;
=======
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;
>>>>>>> b4fe49da74e22353f02bad884cadc1c7d6387f5a

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
	@Autowired
<<<<<<< HEAD
	private CardapioMktplaceProxy cardapioMktplace;
	
=======
	@Qualifier("faixaFreteServiceImpl")
	FaixaFreteService serviceFF;
	
	@Autowired
	@Qualifier("freteServiceImpl")
	FreteService serviceF;
	
>>>>>>> b4fe49da74e22353f02bad884cadc1c7d6387f5a
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
<<<<<<< HEAD
			System.out.println(cardapioMktplace.buscarRestaurantePor("rest", "39"));
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
			
>>>>>>> b4fe49da74e22353f02bad884cadc1c7d6387f5a
		};
	}


}