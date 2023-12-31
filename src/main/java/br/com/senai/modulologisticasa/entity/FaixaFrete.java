package br.com.senai.modulologisticasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "faixasFrete")
@Entity(name = "FaixaFrete")
public class FaixaFrete {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Positive
	@NotNull(message = "O valor mínimo do km é obrigatório")
	@Column(name = "kmMin")
	private Integer kmMin;
	
	@Positive
	@NotNull(message = "O valor máximo do km é obrigatório")
	@Column(name = "kmMax")
	private Integer kmMax;
	
	@Positive
	@NotNull(message = "O valor do km é obrigtório")
	@Column(name = "valorKm")
	private Integer valorKm;
	
	@Transient
	@JsonIgnore
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
}
