package br.com.senai.modulologisticasa.service.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import br.com.senai.modulologisticasa.dto.Cliente;
import br.com.senai.modulologisticasa.dto.EnderecoCliente;
import br.com.senai.modulologisticasa.dto.EnderecoRestaurante;
import br.com.senai.modulologisticasa.dto.Pedido;
import br.com.senai.modulologisticasa.dto.QtdItem;
import br.com.senai.modulologisticasa.dto.Restaurante;
import br.com.senai.modulologisticasa.dto.enuns.Retirada;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import br.com.senai.modulologisticasa.service.PedidoService;

public class PedidoServiceProxy implements PedidoService {
	
	@Autowired
	private ProducerTemplate getPedidosAceitos;
	
	@Override
	public List<Pedido> listarPor(Status status, Retirada retirada, Pageable paginacao) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("status", status);
		requestBody.put("tipo de entrega", retirada);
		requestBody.put("paginacaoAtual", paginacao);
		JSONObject pedidoJson = getPedidosAceitos.requestBody(
				"direct:listarPor", requestBody, JSONObject.class);
		
		Integer totalDeItens = Integer.valueOf(pedidoJson.getInt("totalDeItens"));
		
		List<Pedido> pedidos = new ArrayList<Pedido>();

		for (int i = 0; i < totalDeItens; i++) {
			
			String nomeCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(8).getString("nome"));
			String ruaCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("rua"));
			String estadoCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("estado"));
			String cidadeCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("cidade"));
			String complementoCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("complemento"));
			String numeroCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("numero"));
			String bairroCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("bairro"));
			String cepCliente = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(2).getString("CEP"));
			EnderecoCliente enderecoCliente = new EnderecoCliente();
			enderecoCliente.setRua(ruaCliente);
			enderecoCliente.setEstado(estadoCliente);
			enderecoCliente.setCidade(cidadeCliente);
			enderecoCliente.setComplemento(complementoCliente);
			enderecoCliente.setNumero(numeroCliente);
			enderecoCliente.setBairro(bairroCliente);
			enderecoCliente.setCep(cepCliente);
			
			Cliente cliente = new Cliente();
			cliente.setEndereco(enderecoCliente);
			cliente.setNome(nomeCliente);
			
			String nomeRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(4).getString("nome"));
			String ruaRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(4).getString("rua"));
			String cidadeRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(4).getString("cidade"));
			String bairroRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(4).getString("bairro"));
			String cepRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONObject(4).getString("CEP"));
			EnderecoRestaurante enderecoRestaurante = new EnderecoRestaurante();
			enderecoRestaurante.setRua(ruaRestaurante);
			enderecoRestaurante.setCidade(cidadeRestaurante);
			enderecoRestaurante.setBairro(bairroRestaurante);
			enderecoRestaurante.setCep(cepRestaurante);
			
			Restaurante restaurante = new Restaurante();
			restaurante.setEndereco(enderecoRestaurante);
			restaurante.setNome(nomeRestaurante);
			
			List<QtdItem> itens = new ArrayList<QtdItem>();
			JSONArray qtdItens = pedidoJson.getJSONArray("listagem").getJSONArray(i)
					.getJSONArray(9);
			for (int i1 = 0; i1 < qtdItens.length() - 1; i1++) {
				String nomeItem = String.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i1)
						.getJSONArray(9).getJSONObject(i1).getString("nome"));
				Integer qtdItem = Integer.valueOf(pedidoJson.getJSONArray("listagem").getJSONArray(i1)
						.getJSONArray(9).getJSONObject(i1).getString("qtde_itens"));
				QtdItem itemAtual = new QtdItem();
				itemAtual.setNome(nomeItem);
				itemAtual.setQuantidade(qtdItem);
				itens.add(itemAtual);
			}
			
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setRestaurante(restaurante);
			pedido.setItens(itens);
			
			pedidos.add(pedido);
			
		}
		
		return pedidos;
		
	}

}
