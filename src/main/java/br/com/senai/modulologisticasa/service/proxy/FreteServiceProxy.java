package br.com.senai.modulologisticasa.service.proxy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.dto.ValorDoFrete;
import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;
import br.com.senai.modulologisticasa.service.GoogleMatrixService;
import br.com.senai.modulologisticasa.service.PedidoService;

@Service
public class FreteServiceProxy implements FreteService{

	@Autowired
	@Qualifier("freteServiceImpl")
	private FreteService freteService;
	
	@Autowired
	@Qualifier("faixaFreteServiceProxy")
	private FaixaFreteService faixaFreteService;
	
	@Autowired
	@Qualifier("googleMatrixServiceProxy")
	private GoogleMatrixService googleMatrixService;
	
	@Autowired
	@Qualifier("pedidoServiceProxy")
	private PedidoService pedidoService;
	
	@Autowired
	private ProducerTemplate toPedidoApi;
	
	@Override
	public Frete salvar(Frete frete) {
		
		return freteService.salvar(frete);
	}

	@Override
	public Frete buscarPor(Integer id) {
		return freteService.buscarPor(id);
	}
	
	@Override
	public Frete buscarPorIdPedido(Integer idPedido) {
		return freteService.buscarPorIdPedido(idPedido);
	}
	
	@Override
	public List<Frete> listarPor(Integer id, Integer mes) {
		return freteService.listarPor(id, mes);
	}

	@Override
	public BigDecimal calcularValorFrete(BigDecimal distancia, FaixaFrete faixaFrete) {
		return freteService.calcularValorFrete(distancia, faixaFrete);
	}
	
	@Override
	public ValorDoFrete calcularFretePor(String cepDeOrigem, String cepDeDestino) {
		BigDecimal distanciaPercorrida = googleMatrixService.buscarDistancia(cepDeOrigem, cepDeDestino).get(0);
		distanciaPercorrida = distanciaPercorrida.divide(BigDecimal.valueOf(1000), 1, RoundingMode.HALF_UP);
		FaixaFrete faixaEncontrada = faixaFreteService.buscarPor(distanciaPercorrida);
		BigDecimal custo = calcularValorFrete(distanciaPercorrida, faixaEncontrada);
		ValorDoFrete valor = new ValorDoFrete();
		valor.setCusto(custo);
		return valor;
	}
	
	@Override
	public void aceitarParaEntregaPor(Integer idDoEntregador, Integer idDoPedido) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("idDoPedido", idDoPedido);
		requestBody.put("status", Status.ACEITO_PARA_ENTREGA);		
		
		this.freteService.aceitarParaEntregaPor(idDoEntregador, idDoPedido);
		
		this.toPedidoApi.requestBody("direct:atualizarStatus", requestBody);		
	}

	@Override
	public void confirmarEntregaPor(Integer idDoEntregador, Integer idDoPedido) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("idDoPedido", idDoPedido);
		requestBody.put("status", Status.ENTREGUE);		
		
		this.freteService.confirmarEntregaPor(idDoEntregador, idDoPedido);

		this.toPedidoApi.requestBody("direct:atualizarStatus", requestBody);		
	}
	
}
