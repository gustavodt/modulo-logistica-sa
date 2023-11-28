package br.com.senai.modulologisticasa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Endereco {
	
	@Size(min = 8, max = 8, message = "O cep não deve conter mais de 45 caracteres")
	@NotBlank(message = "O cep é obrigatório")
	private String cep;
	
	@Size(max = 45, message = "O rua não deve conter mais de 45 caracteres")
	@NotBlank(message = "A rua é obrigatória")
	private String rua;
	
	@Size(max = 45, message = "A cidade não deve conter mais de 45 caracteres")
	@NotBlank(message = "A cidade é obrigatória")
	private String cidade;
	
	@Size(max = 45, message = "O bairro não deve conter mais de 45 caracteres")
	@NotBlank(message = "O bairro é obrigatório")
	private String bairro;
	
	@Size(max = 45, message = "O estado não deve conter mais de 45 caracteres")
	@NotBlank(message = "O estado é obrigatório")
	private String estado;
	
	@Size(max = 45, message = "O numero não deve conter mais de 45 caracteres")
	@NotBlank(message = "O numero é obrigatório")
	private String numero;
	
	private String complemento;
	
}
