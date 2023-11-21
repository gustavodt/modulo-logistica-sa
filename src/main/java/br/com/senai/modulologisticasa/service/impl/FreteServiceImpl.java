package br.com.senai.modulologisticasa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FreteService;

@Service
public class FreteServiceImpl implements FreteService {

	@Autowired
	private FretesRepository repository;

	@Override
	public Frete salvar(Frete frete) {
		Frete freteSalvo = repository.save(frete);
		return repository.buscarPorId(freteSalvo.getId());
	}

	@Override
	public void atualizarStatusPor(Integer id, Integer status) {
		
		Frete freteEncontrado = repository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não existe Frete vinculado ao ID informado");
		Preconditions.checkArgument(freteEncontrado.getStatus() != status,
				"O status já esta salvo para o Frete");
		this.repository.atualizarPor(id, status);
		
	}

	@Override
	public Frete buscarPor(Integer id) {
		Frete freteEncontrado = repository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não foi encontrado frete para o id informado");
		return freteEncontrado;
	}

	@Override
	public List<Frete> listarPor(Integer ano, Integer mes, Integer status) {
		List<Frete> fretes = repository.listarPor(ano, mes, status);
		Preconditions.checkNotNull(fretes, "Fretes não encontrados");
		return fretes;
	}
	
	
	
}
