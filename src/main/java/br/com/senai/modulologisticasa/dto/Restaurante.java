package br.com.senai.modulologisticasa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Restaurante {
	
	@Size(max = 45, message = "O nome não deve conter mais de 45 caracteres")
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotNull(message = "O endereço é obrigatório")
	private EnderecoRestaurante endereco;
	
}
