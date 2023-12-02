package br.com.senai.modulologisticasa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Pedido {
	
	@NotBlank(message = "O cep do cliente é obrigatório")
	private String cepCliente;
	
	@NotBlank(message = "O cep do restaurante é obrigatório")
	private String cepRestaurante;
	
}
