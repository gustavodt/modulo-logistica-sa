package br.com.senai.modulologisticasa.service.proxy;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.dto.Pedido;
import br.com.senai.modulologisticasa.service.PedidoService;

@Service
public class PedidoServiceProxy implements PedidoService {
	
	@Autowired
	private ProducerTemplate getPedidosAceitos;
	
	@Override
	public Pedido buscarPorId(Integer idPedido) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("idPedido", idPedido);
		JSONObject pedidoJson = getPedidosAceitos.requestBody(
				"direct:buscarPedido", requestBody, JSONObject.class);
		
		String cepCliente = String.valueOf(pedidoJson.getJSONObject("endereco").getString("cep"));
		
		String cepRestaurante = String.valueOf(pedidoJson.getJSONObject("restaurante").getString("cep"));
		
		Pedido pedido = new Pedido();
		pedido.setCepCliente(cepCliente);
		pedido.setCepRestaurante(cepRestaurante);
		
		return pedido;
		
	}

}
