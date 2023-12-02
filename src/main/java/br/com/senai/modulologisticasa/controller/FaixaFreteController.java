
package br.com.senai.modulologisticasa.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.service.FaixaFreteService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/faixaFrete")
public class FaixaFreteController {
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("faixaFreteServiceProxy")
	private FaixaFreteService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> inserir(FaixaFrete faixaFrete) {
		FaixaFrete FaixaFreteSalva = service.inserir(faixaFrete);		
		return ResponseEntity.created(URI.create(
				"/faixaFrete/id/" + FaixaFreteSalva.getId())).build();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("id") 
			Integer id) {
		FaixaFrete FaixaFreteEncontrada = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(FaixaFreteEncontrada));
	}
	
}
