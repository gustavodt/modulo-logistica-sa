package br.com.senai.modulologisticasa.service.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.service.FreteService;

@Service
public class FreteServiceProxy implements FreteService{

	@Autowired
	@Qualifier("freteServiceImpl")
	private FreteService service;

	@Override
	public Frete salvar(Frete frete) {
		return service.salvar(frete);
	}

	@Override
	public void atualizarStatusPor(Integer id, Integer status) {
		this.service.atualizarStatusPor(id, status);
	}

	@Override
	public Frete buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public List<Frete> listarPor(Integer ano, Integer mes, Integer status) {
		return service.listarPor(ano, mes, status);
	}
	
	
	
	
}
