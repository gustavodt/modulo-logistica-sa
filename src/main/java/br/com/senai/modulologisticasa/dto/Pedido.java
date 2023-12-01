package br.com.senai.modulologisticasa.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Pedido {
	
	@NotNull(message = "O cliente é obrigatório")
	private Cliente cliente;
	
	@NotNull(message = "Os itens são obrigatórios")
	private List<QtdItem> itens;
	
	@NotNull(message = "O restaurante é obrigatório")
	private Restaurante restaurante;
	
}
