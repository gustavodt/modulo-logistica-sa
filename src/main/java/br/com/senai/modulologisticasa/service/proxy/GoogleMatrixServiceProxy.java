package br.com.senai.modulologisticasa.service.proxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.service.GoogleMatrixService;

@Service 
public class GoogleMatrixServiceProxy implements GoogleMatrixService{

	@Autowired
	private ProducerTemplate getGoogleMatrix;

	@Override
	public List<BigDecimal> buscarDistancia(String origem, String destino) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("cepDeOrigem", origem);
		requestBody.put("cepDeDestino", destino);
		JSONObject distanciaJson = getGoogleMatrix.requestBody(
				"direct:buscarDistancia", requestBody, JSONObject.class);
		
		List<BigDecimal> distanciaTempo = new ArrayList<>();
		
		BigDecimal distancia = BigDecimal.valueOf(distanciaJson.getJSONArray("rows").getJSONObject(0)
				.getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value"));
		
		BigDecimal tempo = BigDecimal.valueOf(distanciaJson.getJSONArray("rows").getJSONObject(0)
				.getJSONArray("elements").getJSONObject(0).getJSONObject("duration").getInt("value"));
		
		distanciaTempo.add(distancia);
		distanciaTempo.add(tempo);
		
		return distanciaTempo;
	}
	
	
}
