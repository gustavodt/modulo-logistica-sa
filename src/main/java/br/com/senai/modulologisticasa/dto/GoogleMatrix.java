package br.com.senai.modulologisticasa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GoogleMatrix {
	
	@Size(max = 9, message = "O cep de origem não pode conter mais de 9 caracteres")
	@NotBlank(message = "O cep de origem é obrigatório")
	private Integer origem;
	
	@Size(max = 9, message = "O cep de destino não pode conter mais de 9 caracteres")
	@NotBlank(message = "O cep de destino é obrigatório")
	private Integer destino;
}
