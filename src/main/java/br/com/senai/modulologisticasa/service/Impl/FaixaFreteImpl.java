package br.com.senai.modulologisticasa.service.Impl;

import java.math.BigDecimal;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.repository.FaixasFreteRepository;
import br.com.senai.modulologisticasa.service.FaixaFreteService;

public class FaixaFreteImpl implements FaixaFreteService {

	private FaixasFreteRepository faixaFreteRepository;
	
	public FaixaFrete inserir(FaixaFrete faixaFrete) {
		FaixaFrete novoFaixaFrete = new FaixaFrete();
		
		novoFaixaFrete.setKmMin(faixaFrete.getKmMin());
		
		novoFaixaFrete.setKmMax(faixaFrete.getKmMax());
		
		novoFaixaFrete.setValorKm(faixaFrete.getValorKm());
		
		return novoFaixaFrete;
	}

	@Override
	public FaixaFrete buscarPor(Integer id) {
		return null;
	}

	@Override
	public FaixaFrete buscarPor(BigDecimal valorKm) {
		return null;
	}

}
