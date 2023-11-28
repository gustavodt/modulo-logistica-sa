package br.com.senai.modulologisticasa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senai.modulologisticasa.dto.Pedido;
import br.com.senai.modulologisticasa.dto.enuns.Retirada;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import jakarta.validation.constraints.NotNull;

public interface PedidoService {
	
	public Page<Pedido> listarPor(
			@NotNull(message = "O status é obrigatorio")
			Status status,
			Retirada retirada,
			Pageable paginacao);
	
}
