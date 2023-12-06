package br.com.senai.modulologisticasa.service.proxy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.dto.Pedido;
import br.com.senai.modulologisticasa.dto.ValorDoFrete;
import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;
import br.com.senai.modulologisticasa.service.GoogleMatrixService;
import br.com.senai.modulologisticasa.service.PedidoService;

@Service
public class FreteServiceProxy implements FreteService{

	@Autowired
	private FretesRepository fretesRepository;
	
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
		
		this.atualizarFretePor(idDoEntregador, idDoPedido, Status.ACEITO_PARA_ENTREGA);
		
		this.toPedidoApi.requestBody("direct:atualizarStatus", requestBody);		
	}

	@Override
	public void confirmarEntregaPor(Integer idDoEntregador, Integer idDoPedido) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("idDoPedido", idDoPedido);
		requestBody.put("status", Status.ENTREGUE);		
		
		this.atualizarFretePor(idDoEntregador, idDoPedido, Status.ENTREGUE);

		this.toPedidoApi.requestBody("direct:atualizarStatus", requestBody);		
	}
	
	@Override
	public void atualizarFretePor(Integer idDoEntregador, Integer idDoPedido, Status status) {
		Frete frete = fretesRepository.buscarPorIdPedido(idDoPedido);
		
		if (frete == null) {
			Frete novoFrete = new Frete();
			
			Pedido novoPedido = pedidoService.buscarPorId(idDoPedido);
			
			novoFrete.setDataMovimento(LocalDateTime.now());
			novoFrete.setStatus(status);
			
			String cepRestaurante = novoPedido.getCepRestaurante();
			String cepCliente = novoPedido.getCepCliente();
			
			List<BigDecimal> distanciaTempo = 
					googleMatrixService.buscarDistancia(cepRestaurante, cepCliente);
			BigDecimal distancia = distanciaTempo.get(0).divide(BigDecimal.valueOf(1000), 1, RoundingMode.HALF_UP);
			BigDecimal tempo = distanciaTempo.get(1).divide(BigDecimal.valueOf(60), 0, RoundingMode.HALF_UP);
			
			novoFrete.setDistancia(distancia);
			novoFrete.setTempoEntregaMinutos(Integer.valueOf(tempo.toString()));
			
			FaixaFrete faixaFreteEncontrada = faixaFreteService.buscarPor(distancia);
			novoFrete.setValorKm(faixaFreteEncontrada.getValorKm());
			
			BigDecimal valorDoFrete = calcularValorFrete(distancia, faixaFreteEncontrada);
			novoFrete.setValorTotal(valorDoFrete);
			
			novoFrete.setIdEntregador(idDoEntregador);
			novoFrete.setIdPedido(idDoPedido);
			
			this.freteService.salvar(novoFrete);
		} else {
			frete.setIdEntregador(idDoEntregador);
			frete.setStatus(status);
			this.freteService.salvar(frete);
		}
	}
	
}
