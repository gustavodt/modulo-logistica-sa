package br.com.senai.modulologisticasa.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface FaixaFreteService {
	
	public FaixaFrete salvar(
			@Valid 
			@NotNull(message = "O frete não pode ser nulo")
			FaixaFrete frete);
	
	public FaixaFrete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id);
	
}
