package br.com.senai.modulologisticasa.service.proxy;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.dto.ValorDoFrete;
import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
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

	@Override
	public Frete salvar(Frete frete) {
		
		return freteService.salvar(frete);
	}

	@Override
	public void atualizarStatusPor(Integer id, Integer status) {
		this.freteService.atualizarStatusPor(id, status);		
	}

	@Override
	public Frete buscarPor(Integer id) {
		return freteService.buscarPor(id);
	}
	@Override
	public List<Frete> listarPor(Integer id, Integer mes, Integer status) {
		return freteService.listarPor(id, mes, status);
	}

	@Override
	public BigDecimal calcularValorFrete(BigDecimal distancia, FaixaFrete faixaFrete) {
		return freteService.calcularValorFrete(distancia, faixaFrete);
	}
	
	@Override
	public ValorDoFrete calcularFretePor(String cepDeOrigem, String cepDeDestino) {
		BigDecimal distanciaPercorrida = googleMatrixService.buscarDistancia(cepDeOrigem, cepDeDestino).get(0);
		FaixaFrete faixaEncontrada = faixaFreteService.buscarPor(distanciaPercorrida);
		BigDecimal custo = calcularValorFrete(distanciaPercorrida, faixaEncontrada);
		ValorDoFrete valor = new ValorDoFrete();
		valor.setCusto(custo);
		return valor;
	}
	
}
