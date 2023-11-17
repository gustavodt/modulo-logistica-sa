package br.com.senai.modulologisticasa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CardapioMktplace {
	
	@NotBlank(message = "O nome do resturante é obrigatório")
	private String nomeRestaurante;
	
	@NotBlank(message = "A categoria é obrigatória")
	private String categoriaRestaurante;

}
