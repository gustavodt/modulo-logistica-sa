package br.com.senai.modulologisticasa.service;


import java.util.List;

import org.springframework.validation.annotation.Validated;


import br.com.senai.modulologisticasa.entity.Frete;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface FreteService {
	
	public Frete salvar(
			@NotNull(message = "O Frete não pode ser nulo")
			Frete frete);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório")
			Integer id,
<<<<<<< HEAD
			@Size(min= 1, max = 5, message = "O status deve ser entre 1 e 5")
=======
			@Min(value = 3)
			@Max(value = 5)
>>>>>>> feature/service
			@NotNull(message = "O novo status não pode ser nulo")
			Integer status);	
	
	public Frete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id);
	
	public List<Frete> listarPor(
			
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório")
			Integer id,
			@Size(min= 1, max = 5, message = "O status deve ser entre 1 e 5")
			@NotNull(message = "O status não pode ser nulo")
			Integer status,
			@Size(min= 1, max= 12, message = "O mês deve ser entre 1 e 12")
			Integer mes);
	
}
