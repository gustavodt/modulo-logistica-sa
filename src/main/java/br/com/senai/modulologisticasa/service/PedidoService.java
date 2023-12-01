package br.com.senai.modulologisticasa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.senai.modulologisticasa.dto.Pedido;
import br.com.senai.modulologisticasa.dto.enuns.Retirada;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import jakarta.validation.constraints.NotNull;

public interface PedidoService {
	
	public List<Pedido> listarPor(
			@NotNull(message = "O status é obrigatorio")
			Status status,
			Retirada retirada,
			Pageable paginacao);
	
}
