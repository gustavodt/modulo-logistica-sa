package br.com.senai.modulologisticasa.service.proxy;

import java.math.BigDecimal;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.service.GoogleMatrixService;

@Service 
public class GoogleMatrixProxy implements GoogleMatrixService{

	@Autowired
	private ProducerTemplate getGoogleMatrix;

	@Override
	public BigDecimal buscarDistancia(String origem, String destino) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("cepDeOrigem", origem);
		requestBody.put("cepDeDestino", destino);
		JSONObject distanciaJson = getGoogleMatrix.requestBody(
				"direct:buscarDistancia", requestBody, JSONObject.class);
		
		BigDecimal distancia = BigDecimal.valueOf(distanciaJson.getJSONArray("rows").getJSONObject(0)
				.getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value"));
		
		return distancia;
	}
	
	
}
