package br.com.senai.modulologisticasa.service.proxy;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.service.CardapioMktplaceService;

@Service
public class CardapioMktplaceProxy implements CardapioMktplaceService{

	@Autowired
	private ProducerTemplate getCardapioMktplaceRestaurante;
	
	@Override
	public String buscarRestaurantePor(String nomeRestaurante, String categoriaRestaurante) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("nomeRestaurante", nomeRestaurante);
		requestBody.put("categoriaRestaurante", categoriaRestaurante);
		JSONObject cardapioJson = getCardapioMktplaceRestaurante.requestBody(
				"direct:buscarRestaurantePor", requestBody, JSONObject.class);
		
		String nomeDoRestaurante = String.valueOf(cardapioJson.getJSONArray("listagem")
				.getJSONObject(0).getString("nome"));
		
		return nomeDoRestaurante;
		
	}

	
	
}
