package br.com.senai.modulologisticasa.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FreteService;

@Service
public class FreteServiceImpl implements FreteService {

	@Autowired
	private FretesRepository fretesRepository;

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
		Preconditions.checkNotNull(freteEncontrado, "Não foi encontrado frete para o id do pedido informado");
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
	
}
