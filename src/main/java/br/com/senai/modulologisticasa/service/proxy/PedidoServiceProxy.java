package br.com.senai.modulologisticasa.service.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senai.modulologisticasa.dto.Cliente;
import br.com.senai.modulologisticasa.dto.Endereco;
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
	public Page<Pedido> listarPor(Status status, Retirada retirada, Pageable paginacao) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("status", status);
		requestBody.put("tipo de entrega", retirada);
		requestBody.put("paginacaoAtual", paginacao);
		JSONObject pedidoJson = getPedidosAceitos.requestBody(
				"direct:listarPor", requestBody, JSONObject.class);
		
		String nomeCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(8).getString("nome"));
		String ruaCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("rua"));
		String estadoCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("estado"));
		String cidadeCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("cidade"));
		String complementoCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("complemento"));
		String numeroCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("numero"));
		String bairroCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("bairro"));
		String cepCliente = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("CEP"));
		Endereco enderecoCliente = new Endereco();
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
		
		String nomeRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(8).getString("nome"));
		String ruaRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("rua"));
		String estadoRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("estado"));
		String cidadeRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("cidade"));
		String complementoRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("complemento"));
		String numeroRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("numero"));
		String bairroRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("bairro"));
		String cepRestaurante = String.valueOf(pedidoJson.getJSONArray("listagem")
				.getJSONObject(2).getString("CEP"));
		Endereco enderecoRestaurante = new Endereco();
		enderecoRestaurante.setRua(ruaRestaurante);
		enderecoRestaurante.setEstado(estadoRestaurante);
		enderecoRestaurante.setCidade(cidadeRestaurante);
		enderecoRestaurante.setComplemento(complementoRestaurante);
		enderecoRestaurante.setNumero(numeroRestaurante);
		enderecoRestaurante.setBairro(bairroRestaurante);
		enderecoRestaurante.setCep(cepRestaurante);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setEndereco(enderecoRestaurante);
		restaurante.setNome(nomeRestaurante);
		
		Integer totalDeItens = Integer.valueOf(pedidoJson.getInt("totalDeItens"));
		List<QtdItem> itens = new ArrayList<QtdItem>();
		for (int i = 0; i < totalDeItens - 1; i++) {
			String nomeItem = String.valueOf(pedidoJson.getJSONArray("listagem")
					.getJSONArray(10).getJSONObject(i).getString("nome"));
			Integer qtdItem = Integer.valueOf(pedidoJson.getJSONArray("listagem")
					.getJSONArray(10).getJSONObject(i).getString("qtde_itens"));
			QtdItem itemAtual = new QtdItem();
			itemAtual.setNome(nomeItem);
			itemAtual.setQuantidade(qtdItem);
			itens.add(itemAtual);
		}
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);
		pedido.setItens(itens);
		
		return null;
	}

}
