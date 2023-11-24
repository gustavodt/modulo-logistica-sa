package br.com.senai.modulologisticasa.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Validated
public interface GoogleMatrixService {


	public List<BigDecimal> buscarDistancia(
			@Size(max = 9, message = "O tamanho do cep deve conter 9 caracteres")
			@NotBlank(message = "O cep de origem é obrigatório")
			String origem,
			@Size(max = 9, message = "O tamanho do cep deve conter 9 caracteres")
			@NotBlank(message = "O cep de destino é obrigatório")
			String destino
			);

}
