package br.com.senai.modulologisticasa.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.senai.modulologisticasa.dto.ValorDoFrete;
import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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
			Status status);	
	
	public Frete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id);
	
	public List<Frete> listarPor(
			@Positive(message = "O ano para busca deve ser positivo")
			@NotNull(message = "O ano é obrigatório")
			Integer ano,
			@Min(value = 1)
			@Max(value = 12)
			@NotNull(message = "O mês não pode ser nulo")
			Integer mes);

	public default BigDecimal calcularValorFrete(
			@DecimalMin(value = "0.0", inclusive = false, message = "O valor da distância deve ser positivo")
		    @Digits(message = "O valor da distância deve possuir o formato 'NN.NN'", integer = 2, fraction = 2)
			@NotNull(message = "O valor da distância é obrigatório")
			BigDecimal distancia,
			@NotNull(message = "A faixa do frete é obrigatória")
			FaixaFrete faixaFrete) {
		throw new RuntimeException("Esse método não foi implementado");
	}
	
	public default ValorDoFrete calcularFretePor(
			@Size(max = 9, message = "O tamanho do cep deve conter 9 caracteres")
			@NotBlank(message = "O cep de origem é obrigatório")
			String origem,
			@Size(max = 9, message = "O tamanho do cep deve conter 9 caracteres")
			@NotBlank(message = "O cep de destino é obrigatório")
			String destino) {
		throw new RuntimeException("Esse método não foi implementado");
	}
	
}
