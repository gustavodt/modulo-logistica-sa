package br.com.senai.modulologisticasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.modulologisticasa.service.EntregadorService;
import br.com.senai.modulologisticasa.service.FreteService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
	@Autowired
	@Qualifier("entregadorServiceProxy")
	EntregadorService serviceE;
	
	@Autowired
	@Qualifier("freteServiceProxy")
	FreteService serviceF;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Raul subiu");
			//System.out.println(service.buscarIdEntregadorPor("teste@gmail.com"));
		};
	}


}