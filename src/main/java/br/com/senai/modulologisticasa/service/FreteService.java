package br.com.senai.modulologisticasa.service;

import org.springframework.validation.annotation.Validated;


import br.com.senai.modulologisticasa.entity.Frete;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface FreteService {
	
	public Frete salvar(
			@NotNull(message = "O Frete não pode ser nulo")
			Frete frete);
	
	public void atualizarStatusPor(
			@Positive(message = "O id para atualização deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id,
			@NotNull(message = "O novo status não pode ser nulo")
			Integer status);	
	
	public Frete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id);
	
}
