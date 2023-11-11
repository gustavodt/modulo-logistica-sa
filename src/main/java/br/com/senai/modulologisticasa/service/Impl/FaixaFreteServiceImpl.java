package br.com.senai.modulologisticasa.service.Impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.repository.FaixasFreteRepository;
import br.com.senai.modulologisticasa.service.FaixaFreteService;

@Service
public class FaixaFreteServiceImpl implements FaixaFreteService {

	private FaixasFreteRepository repository;
	
	public FaixaFrete inserir(FaixaFrete faixaFrete) {
		
		for (FaixaFrete faixaFreteEscolhida : repository.listarTodos()) {
			verificarConflitoFaixasFrete(faixaFreteEscolhida, faixaFrete);
		}
		
		FaixaFrete faixaSalva = repository.save(faixaFrete);
		return repository.buscarPorId(faixaSalva.getId());
	}
	
	private void verificarConflitoFaixasFrete(FaixaFrete faixaFreteAntiga, FaixaFrete faixaFreteNova) {
		Preconditions.checkArgument(
				(faixaFreteAntiga.getKmMin() < faixaFreteNova.getKmMin()
						&& faixaFreteAntiga.getKmMax() < faixaFreteNova.getKmMax())
				|| (faixaFreteAntiga.getKmMin() > faixaFreteNova.getKmMin()
						&& faixaFreteAntiga.getKmMax() > faixaFreteNova.getKmMax())
				|| (faixaFreteAntiga.getKmMin() > faixaFreteNova.getKmMin()
						&& faixaFreteAntiga.getKmMax() < faixaFreteNova.getKmMax())
				|| (faixaFreteAntiga.getKmMin() < faixaFreteNova.getKmMin()
						&& faixaFreteAntiga.getKmMax() > faixaFreteNova.getKmMax()),
				"Faixas de frete em conflito");
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
