package br.com.senai.modulologisticasa.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.senai.modulologisticasa.entity.Frete;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface FreteService {
	
	public Frete salvar(
			@NotNull(message = "O Frete não pode ser nulo")
			Frete frete);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório")
			Integer id,
			@Min(value = 3)
			@Max(value = 5)
			@NotNull(message = "O novo status não pode ser nulo")
			Integer status);	
	
	public Frete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id);
	
	public List<Frete> listarPor(
			@Positive(message = "O ano para listagem deve ser positivo")
			@NotNull(message = "O ano é obrigatório")
			Integer ano,
			@Positive(message = "O mes para listagem deve ser positivo")
			@NotNull(message = "O mes é obrigatório")
			Integer mes,
			@Positive(message = "O status para listagem deve ser positivo")
			@NotNull(message = "O status é obrigatório")
			Integer status);
	
}
