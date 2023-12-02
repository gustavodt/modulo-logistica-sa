package br.com.senai.modulologisticasa.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValorDoFrete {
	
	@DecimalMin(value = "0.0", inclusive = false, message = "O custo deve ser positivo")
    @Digits(message = "O custo deve possuir o formato 'NN.NN'", integer = 2, fraction = 2)
	@NotNull(message = "O valor do quilometro é obrigatório")
	private BigDecimal custo;
	
}
