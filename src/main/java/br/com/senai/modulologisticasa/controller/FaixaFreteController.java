
package br.com.senai.modulologisticasa.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import jakarta.transaction.Transactional;

public class FaixaFreteController {
	
	@Autowired
	@Qualifier("faixaFreteServiceProxy")
	private FaixaFreteService service;
	
	private Map<String, Object> converter(FaixaFrete faixaFrete){		
		Map<String, Object> faixaFreteMap = new HashMap<String, Object>();
		faixaFreteMap.put("id", faixaFrete.getId());
		faixaFreteMap.put("kmMin", faixaFrete.getKmMin());
		faixaFreteMap.put("kmMax", faixaFrete.getKmMax());
		faixaFreteMap.put("valorKm", faixaFrete.getValorKm());		
		return faixaFreteMap;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> inserir(FaixaFrete faixaFrete) {
		FaixaFrete FaixaFreteSalva = service.inserir(faixaFrete);		
		return ResponseEntity.created(URI.create(
				"/cardapios/id/" + FaixaFreteSalva.getId())).build();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("id") 
			Integer id) {
		FaixaFrete FaixaFreteEncontrada = service.buscarPor(id);
		return ResponseEntity.ok(converter(FaixaFreteEncontrada));
	}
	
	@GetMapping("/distanciaPercorrida/{distanciaPercorrida}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("distanciaPercorrida") 
			BigDecimal distanciaPercorrida) {
		FaixaFrete FaixaFreteEncontrada = service.buscarPor(distanciaPercorrida);
		return ResponseEntity.ok(converter(FaixaFreteEncontrada));
	}
	
}
