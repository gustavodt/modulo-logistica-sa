package br.com.senai.modulologisticasa.service.proxy;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.modulologisticasa.service.EntregadorService;

@Component
public class EntregadorServiceProxy implements EntregadorService {
	
	@Autowired
	private ProducerTemplate getIdEntregador;
	
	@Override
	public Integer buscarIdEntregadorPor(Integer emailEntregador) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("email", emailEntregador);
		JSONObject pedidoJson = getIdEntregador.requestBody(
				"direct:buscarIdEntregador", requestBody, JSONObject.class);
		
		Integer idEntregador = Integer.valueOf(pedidoJson.getInt("id"));
		
		return idEntregador;
	}

}
