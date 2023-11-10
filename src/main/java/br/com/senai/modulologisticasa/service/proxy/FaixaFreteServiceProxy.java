package br.com.senai.modulologisticasa.service.proxy;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import br.com.senai.modulologisticasa.service.Impl.FaixaFreteServiceImpl;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class FaixaFreteServiceProxy implements FaixaFreteService{
	
	@Autowired
	@Qualifier(value = "faixaFreteServiceImpl")
	FaixaFreteServiceImpl service;
	
	@Override
	public FaixaFrete inserir(FaixaFrete frete) {
		return service.inserir(frete);
	}

	@Override
	public FaixaFrete buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public FaixaFrete buscarPor(BigDecimal distanciaPercorrida) {
		return service.buscarPor(distanciaPercorrida);
	}
	
}
