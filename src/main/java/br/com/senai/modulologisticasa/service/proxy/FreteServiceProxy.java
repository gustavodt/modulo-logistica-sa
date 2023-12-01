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

@Service
public class FreteServiceProxy implements FreteService{

	@Autowired
	@Qualifier("freteServiceImpl")
	private FreteService freteService;
	
	@Autowired
	@Qualifier("faixaFreteServiceImpl")
	private FaixaFreteService faixaFreteService;
	
	@Autowired
	@Qualifier("googleMatrixServiceProxy")
	private GoogleMatrixService googleMatrixService;
	
	@Autowired
	private ProducerTemplate patchAtualizarStatus;

	@Override
	public Frete salvar(Frete frete) {
		
		return freteService.salvar(frete);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status, Integer idPedido) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("id", id);
		requestBody.put("status", status);
		patchAtualizarStatus.requestBody(
				"direct:atualizarStatus", requestBody, JSONObject.class);

		Frete freteEncontrado = buscarPorIdPedido(idPedido);
		
		if (freteEncontrado == null) {
			//Frete novoFrete = new Frete();
			//fazer uma rota para buscar os ceps do cliente e restaurante
			//usar o google matrix para descobrir a distancia e o tempo de entrega em minutos
			//usar o service do frete para calcular o valor do frete
			//salvar o resto das informações
			//salvar(frete);
		} else {		
			this.freteService.atualizarStatusPor(freteEncontrado.getId(), status, idPedido);
		}
		
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
	
}
