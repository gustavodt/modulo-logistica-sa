package br.com.senai.modulologisticasa.service;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
public interface CardapioMktplaceService {

	public String buscarRestaurantePor(
			@NotBlank(message = "O nome do restaurante é obrigatório")
			String nomeRestaurante, 
			@NotBlank(message = "A categoria do resturante é obrigatória")
			String categoriaRestaurante);
}
