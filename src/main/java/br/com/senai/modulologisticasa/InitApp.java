package br.com.senai.modulologisticasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.modulologisticasa.repository.FaixasFreteRepository;
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

	@Autowired
	 private FaixasFreteRepository repositoryFF;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			//FaixaFrete faixaFrete = new FaixaFrete();
			//faixaFrete.setKmMin(15);
			//faixaFrete.setKmMax(20);
			//faixaFrete.setValorKm(BigDecimal.valueOf(15.0));
			//serviceFF.salvar(faixaFrete);
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
			//serviceF.atualizarStatusPor(4, 4);
			//System.out.println(frete);
			
		};
	}


}
