package br.com.senai.modulologisticasa.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.Impl.FaixaFreteServiceImpl;

public class FaixaFreteServiceProxy implements FaixaFreteService{
	
	/*@Autowired
	@Qualifier(value = "faixaFreteServiceImpl")
	FaixaFreteServiceImpl service;*/
	
	@Override
	public FaixaFrete inserir(FaixaFrete frete) {
		//return service.inserir(frete);
		return null;
	}

	@Override
	public FaixaFrete buscarPor(Integer id) {
		//return service.buscarPor(id);
		return null;
	}
	
}