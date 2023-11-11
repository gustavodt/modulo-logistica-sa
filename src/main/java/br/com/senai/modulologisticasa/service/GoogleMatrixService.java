package br.com.senai.modulologisticasa.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.modulologisticasa.dto.GoogleMatrix;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Validated
public interface GoogleMatrixService {


	public GoogleMatrix buscarDistancia(
			@Size(max = 9, message = "O cep de origem não pode conter mais de 9 caracteres")
			@NotBlank(message = "O cep de origem é obrigatório")
			Integer origem,
			@Size(max = 9, message = "O cep de destino não pode conter mais de 9 caracteres")
			@NotBlank(message = "O cep de destino é obrigatório")
			Integer destino
			);
}
