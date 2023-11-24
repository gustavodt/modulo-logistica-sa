package br.com.senai.modulologisticasa;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;
import br.com.senai.modulologisticasa.service.proxy.FreteServiceProxy;
import br.com.senai.modulologisticasa.service.proxy.GoogleMatrixProxy;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
//	@Autowired
//	private CardapioMktplaceProxy cardapioMktplace;
	
	@Autowired
	@Qualifier("faixaFreteServiceImpl")
	FaixaFreteService serviceFF;
	
	@Autowired
	@Qualifier("freteServiceImpl")
	FreteService serviceF;
	
	@Autowired
	private GoogleMatrixProxy googleMatrix;
	
	public Integer cepOrigem = 88715000;
	
	public Integer cepDestino = 88701021;
		
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			//System.out.println(googleMatrix.buscarDistancia("88715000", "88701045"));
			System.out.println(serviceF.calcularValorFrete(BigDecimal.valueOf(10)));
				
		};
	}


}