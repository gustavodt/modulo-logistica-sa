package br.com.senai.modulologisticasa.service;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface FaixaFreteService {
	
	public FaixaFrete inserir(
			@Valid 
			@NotNull(message = "O frete não pode ser nulo")
			FaixaFrete frete);
	
	public FaixaFrete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id);
	
	public FaixaFrete buscarPor(
			@DecimalMin(value = "0.0", inclusive = false, message = "O valor do km deve ser positivo")
		    @Digits(message = "O valor do km deve possuir o formato 'NN.NN'", integer = 2, fraction = 2)
			@Positive(message = "O valor do km para busca deve ser positivo")
			@NotNull(message = "O valor do km é obrigatório")
			BigDecimal valorKm);
	
}
