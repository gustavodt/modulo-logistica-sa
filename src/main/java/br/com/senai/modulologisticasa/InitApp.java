package br.com.senai.modulologisticasa;

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
import br.com.senai.modulologisticasa.service.proxy.GoogleMatrixServiceProxy;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
//	@Autowired
//	private CardapioMktplaceProxy cardapioMktplace;
	
	@Qualifier("faixaFreteServiceImpl")
	FaixaFreteService serviceFF;
	
	@Autowired
	@Qualifier("freteServiceProxy")
	FreteService serviceF;
	
	@Autowired
	private GoogleMatrixServiceProxy getGoogleMatrix;
	
	private GoogleMatrixServiceProxy googleMatrix;
	
	public Integer cepOrigem = 88715000;
	
	public Integer cepDestino = 88701021;
		
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			System.out.println(serviceF.calcularFretePor("88715000", "88715001"));
			
		};
	}


}