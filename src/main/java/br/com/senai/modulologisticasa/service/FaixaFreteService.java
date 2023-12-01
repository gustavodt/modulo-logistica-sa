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
			@DecimalMin(value = "0.0", inclusive = false, message = "A distância percorrida deve ser positiva")
		    @Digits(message = "A distância percorrida deve possuir o formato 'NN.NN'", integer = 12, fraction = 2)
			@NotNull(message = "A distância percorrida é obrigatório")
			BigDecimal distanciaPercorrida);
	
}
