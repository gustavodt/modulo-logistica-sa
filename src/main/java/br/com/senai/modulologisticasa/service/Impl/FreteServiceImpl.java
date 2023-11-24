package br.com.senai.modulologisticasa.service.Impl;

<<<<<<< HEAD
=======
import java.math.BigDecimal;
>>>>>>> feature/service
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.repository.FaixasFreteRepository;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.FreteService;

@Service
public class FreteServiceImpl implements FreteService {

	@Autowired
	private FretesRepository fretesRepository;
	
	@Autowired
	private FaixasFreteRepository faixasFreteRepository;
	
	@Autowired
	@Qualifier("faixaFreteServiceProxy")
	private FaixaFreteService faixaFreteService;

	@Override
	public Frete salvar(Frete frete) {
		Frete freteSalvo = fretesRepository.save(frete);
		return fretesRepository.buscarPorId(freteSalvo.getId());
	}

	@Override
	public void atualizarStatusPor(Integer id, Integer status) {
		
		Frete freteEncontrado = fretesRepository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não existe Frete vinculado ao ID informado");
		Preconditions.checkArgument(freteEncontrado.getStatus() != status,
				"O status já esta salvo para o Frete");
		this.fretesRepository.atualizarPor(id, status);
		
	}

	@Override
	public Frete buscarPor(Integer id) {
		Frete freteEncontrado = fretesRepository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não foi encontrado frete para o id informado");
		return freteEncontrado;
	}

	@Override
	public BigDecimal calcularValorFrete(BigDecimal distancia) {
		List<FaixaFrete> faixasFrete = faixasFreteRepository.listarTodos();
		BigDecimal valorTotal = null;
		for (FaixaFrete faixaFrete : faixasFrete) {
			if ((distancia.compareTo(BigDecimal.valueOf(faixaFrete.getKmMin())) == 0
				|| distancia.compareTo(BigDecimal.valueOf(faixaFrete.getKmMin())) == 1)
					&& distancia.compareTo(BigDecimal.valueOf(faixaFrete.getKmMax())) == -1) {
				valorTotal = distancia.multiply(BigDecimal.valueOf(faixaFrete.getValorKm()));
				return valorTotal;
			} else {
				if (faixasFrete.get(faixasFrete.size() - 1).getKmMin() == faixaFrete.getKmMin()) {
					valorTotal = distancia.multiply(BigDecimal.valueOf(faixaFrete.getValorKm()));
					return valorTotal;
				}
			}
		}
		throw new RuntimeException("Não foi possívle calcular o valor do frete");
	}
	
	@Override
	public List<Frete> listarPor(Integer id,Integer mes, Integer status) {
		return repository.listarPor(id, mes, status);
	}
	
}
