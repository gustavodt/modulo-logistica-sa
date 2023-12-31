package br.com.senai.modulologisticasa.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.exception.BusinessException;
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
			throw new BusinessException("KmMin da faixa de frete precisa ser igual ao kmMax da faixa de frete anterior");
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
			throw new BusinessException("Faixas de frete em conflito");
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
		
		List<FaixaFrete> faixasFrete = repository.listarTodos();
		for (FaixaFrete faixaFrete : faixasFrete) {
			if ((distanciaPercorrida.compareTo(BigDecimal.valueOf(faixaFrete.getKmMin())) == 0
				|| distanciaPercorrida.compareTo(BigDecimal.valueOf(faixaFrete.getKmMin())) == 1)
					&& distanciaPercorrida.compareTo(BigDecimal.valueOf(faixaFrete.getKmMax())) == -1) {
				return faixaFrete;
			} else {
				if (faixasFrete.get(faixasFrete.size() - 1).getKmMin() == faixaFrete.getKmMin()) {
					throw new BusinessException("A distância está fora das faixas cadastradas");
				}
			}
		}
		throw new BusinessException("Não foi possível encontrar faixa de frete");
	}

}
