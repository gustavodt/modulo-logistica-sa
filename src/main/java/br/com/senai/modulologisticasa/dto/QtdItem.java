package br.com.senai.modulologisticasa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QtdItem {
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotNull(message = "A quantidade é obrigatória")
	private Integer quantidade;
	
}
