package br.com.senai.modulologisticasa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senai.modulologisticasa.entity.enuns.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
	
	@Positive
	@NotNull(message = "O valor de cada km é obrigtório")
	@Column(name = "valorKm")
	private Integer valorKm;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor da distância deve ser positivo")
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
	
	@NotNull(message = "O status do pedido é obrigatorio")
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@Positive
	@NotNull(message = "O id do entregador é orbrigatório")
	@Column(name = "idEntregador")
	private Integer idEntregador;
	
	@Positive
	@NotNull(message = "O tempo de entrega do pedido é obrigatorio")
	@Column(name = "tempoEntregaMinutos")
	private Integer tempoEntregaMinutos;
	
	@Transient
	@JsonIgnore
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	@Transient
	@JsonIgnore
	public boolean isStatusProntoParaColeta() {
		return getStatus() == Status.PRONTO_PARA_COLETA;
	}
	
	@Transient
	@JsonIgnore
	public boolean isStatusAceitoParaEntrega() {
		return getStatus() == Status.ACEITO_PARA_ENTREGA;
	}
	
	@Transient
	@JsonIgnore
	public boolean isStatusEntregue() {
		return getStatus() == Status.ENTREGUE;
	}
	
}
