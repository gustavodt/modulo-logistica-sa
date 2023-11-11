package br.com.senai.modulologisticasa.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FreteService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class FreteServiceImpl implements FreteService {

	@Autowired
	private FretesRepository repository;

	@Override
	public Frete salvar(@NotNull(message = "O Frete não pode ser nulo") 
	Frete frete) {
		return frete;
	}

	@Override
	public void atualizarStatusPor(
			@Positive(message = "O id para atualização deve ser positivo") 
			@NotNull(message = "O id é obrigatório") 
			Integer id,
			@NotNull(message = "O novo status não pode ser nulo") 
			Integer status) {
		
		Frete freteEncontrado = repository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não existe Frete vinculado ao ID informado");
		Preconditions.checkArgument(freteEncontrado.getStatus() != status,
				"O status já esta salvo para o Frete");
		this.repository.atualizarPor(id, status);
		
	}

	@Override
	public Frete buscarPor(
			@Positive(message = "O id para busca deve ser positivo") 
			@NotNull(message = "O id é obrigatório") 
			Integer id) {
		Frete freteEncontrado = repository.buscarPorId(id);
		Preconditions.checkNotNull(freteEncontrado, "Não foi encontrado frete para o id informado");
		return freteEncontrado;
	}
	
	
	
}
