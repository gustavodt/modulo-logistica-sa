package br.com.senai.modulologisticasa;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ProducerTemplate getGoogleMatrix;
		
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			JSONObject requestBody = new JSONObject();
			requestBody.put("cepDeOrigem", "88715000");
			requestBody.put("cepDeDestino", "88701021");
			JSONObject distanciaJson = getGoogleMatrix.requestBody(
					"direct:buscarDistancia", requestBody, JSONObject.class);
			System.out.println(distanciaJson.getJSONArray("rows").getJSONObject(0)
					.getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value"));
		};
	}


}
