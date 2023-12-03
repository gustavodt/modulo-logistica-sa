package br.com.senai.modulologisticasa.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.dto.Pedido;
import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;
import br.com.senai.modulologisticasa.service.GoogleMatrixService;
import br.com.senai.modulologisticasa.service.PedidoService;

@Service
public class FreteServiceImpl implements FreteService {

	@Autowired
	private FretesRepository fretesRepository;
	
	@Autowired
	@Qualifier("faixaFreteServiceProxy")
	private FaixaFreteService faixaFreteService;
	
	@Autowired
	@Qualifier("googleMatrixServiceProxy")
	private GoogleMatrixService googleMatrixService;
	
	@Autowired
	@Qualifier("pedidoServiceProxy")
	private PedidoService pedidoService;

	@Override
	public Frete salvar(Frete frete) {
		Frete freteSalvo = fretesRepository.save(frete);
		return fretesRepository.buscarPorId(freteSalvo.getId());
	}

	@Override
	public Frete buscarPor(Integer id) {
		Frete freteEncontrado = fretesRepository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não foi encontrado frete para o id informado");
		return freteEncontrado;
	}
	
	@Override
	public Frete buscarPorIdPedido(Integer idPedido) {
		Frete freteEncontrado = fretesRepository.buscarPorIdPedido(idPedido);
		Preconditions.checkNotNull(freteEncontrado, "Não foi encontrado frete para o id de pedido informado");
		return freteEncontrado;
	}

	@Override
	public BigDecimal calcularValorFrete(BigDecimal distancia, FaixaFrete faixaFrete) {
		BigDecimal valorTotal = distancia.multiply(BigDecimal.valueOf(faixaFrete.getValorKm()));
		return valorTotal;
	}
	
	@Override
	public List<Frete> listarPor(Integer ano, Integer mes) {
		List<Frete> fretes = fretesRepository.listarPor(ano, mes);
		Preconditions.checkNotNull(fretes, "Lista de fretes vazia");
		return fretes;
	}
		
	@Override
	public void aceitarParaEntregaPor(Integer idDoEntregador, Integer idDoPedido) {
		Frete frete = fretesRepository.buscarPorIdPedido(idDoPedido);
		
		if (frete == null) {
			Frete novoFrete = new Frete();
			
			Pedido novoPedido = pedidoService.buscarPorId(idDoPedido);
			
			novoFrete.setDataMovimento(LocalDateTime.now());
			novoFrete.setStatus(Status.ACEITO_PARA_ENTREGA);
			
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
			
			this.fretesRepository.save(novoFrete);
		} else {
			frete.setIdEntregador(idDoEntregador);
			frete.setStatus(Status.ACEITO_PARA_ENTREGA);
			this.fretesRepository.save(frete);
		}
	}
	
	@Override
	public void confirmarEntregaPor(Integer idDoEntregador, Integer idDoPedido) {
		Frete frete = fretesRepository.buscarPorIdPedido(idDoPedido);
		
		if (frete == null) {
			Frete novoFrete = new Frete();
			
			Pedido novoPedido = pedidoService.buscarPorId(idDoPedido);
			
			novoFrete.setDataMovimento(LocalDateTime.now());
			novoFrete.setStatus(Status.ACEITO_PARA_ENTREGA);
			
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
			
			this.fretesRepository.save(novoFrete);
		} else {
			frete.setIdEntregador(idDoEntregador);
			frete.setStatus(Status.ENTREGUE);
			this.fretesRepository.save(frete);
		}		
	}
	
}
