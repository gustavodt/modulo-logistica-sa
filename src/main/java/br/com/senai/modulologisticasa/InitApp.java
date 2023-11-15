package br.com.senai.modulologisticasa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.modulologisticasa.dto.GoogleMatrix;
import br.com.senai.modulologisticasa.service.proxy.GoogleMatrixProxy;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);		
	}
	
<<<<<<< HEAD
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
			
=======
	@Autowired
	private ProducerTemplate getGoogleMatrix;
	
	@Autowired
	private GoogleMatrixProxy googleMatrix;
	
	public Integer cepOrigem = 88715000;
	
	public Integer cepDestino = 88701021;
		
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			JSONObject requestBody = new JSONObject();
			requestBody.put("cepDeOrigem", cepOrigem.toString());
			requestBody.put("cepDeDestino", cepDestino.toString());
			JSONObject distanciaJson = getGoogleMatrix.requestBody(
					"direct:buscarDistancia", requestBody, JSONObject.class);
			System.out.println(distanciaJson.getJSONArray("rows").getJSONObject(0)
					.getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value"));
			
			googleMatrix.buscarDistancia("88715000", "88715000");
>>>>>>> 98e08749e63d17a08b7b50dabf528c1e271dae53
		};
	}


}
