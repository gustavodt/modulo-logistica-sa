package br.com.senai.modulologisticasa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "fretes")
@Entity(name = "Frete")
public class Frete {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser positivo")
    @Digits(message = "O valor do km deve possuir o formato 'N.NN'", integer = 1, fraction = 2)
	@NotNull(message = "O valor do quilometro é obrigatório")
	@Column(name = "valorKm")
	private BigDecimal valorKm;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser positivo")
    @Digits(message = "O valor da distancia deve possuir o formato 'NN.NN'", integer = 2, fraction = 2)
	@NotNull(message = "O valor da distancia é obrigatória")
	@Column(name = "distancia")
	private BigDecimal distancia;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser positivo")
    @Digits(message = "O valor do km deve possuir o formato 'NNN.NN'", integer = 3, fraction = 2)
	@NotNull(message = "O valor total é obrigtório")
	@Column(name = "valorTotal")
	private BigDecimal valorTotal;
	
	@Positive
	@NotNull(message = "O id do pedido é obrigatório")
	@Column(name = "idPedido")
	private Integer idPedido;
	
	@NotNull(message = "A data do movimento é obrigatório")
	@Column(name = "dataMovimento")
	private LocalDateTime dataMovimento;
	
	@Min(value = 3)
	@Max(value = 5)
	@NotNull(message = "O status do pedido é obrigatorio")
	@Column(name = "status")
	private Integer status;
	
	@Positive
	@NotNull(message = "O id do entregador é orbrigatório")
	@Column(name = "idEntregador")
	private Integer idEntregador;
	
	@Positive
	@NotNull(message = "O tempo de entrega do pedido é obrigatorio")
	@Column(name = "tempoEntregaMinutos")
	private Integer tempoEntregaMinutos;
	
	@Transient
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
}
