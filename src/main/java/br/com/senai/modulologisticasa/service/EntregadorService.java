package br.com.senai.modulologisticasa.service;

import jakarta.validation.constraints.NotBlank;

public interface EntregadorService {
	
	public Integer buscarIdEntregadorPor(
			@NotBlank(message = "O email é obrigatório")
			String emailEntregador);
	
}
