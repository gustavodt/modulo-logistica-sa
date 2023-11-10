package br.com.senai.modulologisticasa.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.service.FreteService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class FreteServiceProxy implements FreteService{

	/*@Autowired
	@Qualifier("freteServiceImpl")
	private FreteService service;*/

	@Override
	public Frete salvar(@NotNull(message = "O Frete não pode ser nulo") Frete frete) {
		
		//return service.salvar(frete);
		return null;
	}

	@Override
	public void atualizarStatusPor(
			@Positive(message = "O id para atualização deve ser positivo")
			@NotNull(message = "O id é obrigatório") 
			Integer id,
			@NotNull(message = "O novo status não pode ser nulo")
			Integer status) {
		//this.service.atualizarStatusPor(id, status);		
	}

	@Override
	public Frete buscarPor(
			@Positive(message = "O id para busca deve ser positivo")
			@NotNull(message = "O id é obrigatório") 
			Integer id) {
		//return service.buscarPor(id);
		return null;
	}
	
	
	
	
}
