package br.com.senai.modulologisticasa.service.Impl;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.repository.FaixasFreteRepository;
import br.com.senai.modulologisticasa.service.FaixaFreteService;

@Service
public class FaixaFreteServiceImpl implements FaixaFreteService {

	@Autowired
	private FaixasFreteRepository repository;
	
	public FaixaFrete inserir(FaixaFrete faixaFrete) {
		FaixaFrete faixaFreteAnterior = repository.validarKmMin(faixaFrete.getKmMin());
		
		if (faixaFreteAnterior != null) {
			
			List<FaixaFrete> faixasFrete = repository.listarTodos();
			
			for (FaixaFrete faixaFreteEscolhida : faixasFrete) {
				verificarConflitoFaixasFrete(faixaFreteEscolhida, faixaFrete);				
			}
			FaixaFrete faixaSalva = repository.save(faixaFrete);
			return repository.buscarPorId(faixaSalva.getId());			
		} else {
			throw new RuntimeException("KmMin da faixa de frete precisa ser igual ao kmMax da faixa de frete anterior");
		}
	}
	
	private void verificarConflitoFaixasFrete(FaixaFrete faixaFreteAntiga, FaixaFrete faixaFreteNova) {
		if ((faixaFreteAntiga.getKmMin() < faixaFreteNova.getKmMin()
				&& faixaFreteAntiga.getKmMax() < faixaFreteNova.getKmMax())
			|| (faixaFreteAntiga.getKmMin() > faixaFreteNova.getKmMin()
					&& faixaFreteAntiga.getKmMax() > faixaFreteNova.getKmMax())
			|| (faixaFreteAntiga.getKmMin() > faixaFreteNova.getKmMin()
					&& faixaFreteAntiga.getKmMax() < faixaFreteNova.getKmMax())
			|| (faixaFreteAntiga.getKmMin() < faixaFreteNova.getKmMin()
					&& faixaFreteAntiga.getKmMax() > faixaFreteNova.getKmMax())) {
		} else {
			throw new RuntimeException("Faixas de frete em conflito");
		};
	}
	
	@Override
	public FaixaFrete buscarPor(Integer id) {
		
		FaixaFrete faixaEncontrada = repository.buscarPorId(id);
		
		Preconditions.checkNotNull(faixaEncontrada, 
				"Não existe opção para o id informado");
		
		return faixaEncontrada;
	}

	@Override
	public FaixaFrete buscarPor(BigDecimal distanciaPercorrida) {
		
		FaixaFrete faixaFrete = new FaixaFrete();
		
		for (FaixaFrete faixaFreteEscolhida : repository.listarTodos()) {
			if (BigDecimal.valueOf(faixaFreteEscolhida.getKmMin()).compareTo(distanciaPercorrida) == 1 
					&& BigDecimal.valueOf(faixaFreteEscolhida.getKmMax()).compareTo(distanciaPercorrida) == -1) {
				faixaFrete = faixaFreteEscolhida;
			}
		}
		
		return faixaFrete;
	}

}
