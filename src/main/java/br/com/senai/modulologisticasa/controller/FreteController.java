package br.com.senai.modulologisticasa.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.dto.ValorDoFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.service.FreteService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/frete")
public class FreteController {

	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("freteServiceProxy")
	private FreteService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Frete frete){
		Preconditions.checkArgument(!frete.isPersistido(),
				"O frete não pode possuir id na inserção");
		Frete freteSalvo = service.salvar(frete);
		
		return ResponseEntity.created(URI.create("/frete/id/"
				+ freteSalvo.getId())).build();
		
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody
			Frete frete){
		Preconditions.checkArgument(frete.isPersistido(),
				"O frete deve possuir id para alteração");
		Frete freteAtualizado = service.salvar(frete);
		return ResponseEntity.ok(converter.toJsonMap(freteAtualizado));
	}
	
	@Transactional
	@PatchMapping("/id/{id}/status/{status}")
	public ResponseEntity<?> atualizarStatusPor(
			@PathVariable("id")
			Integer id,
			@PathVariable("status")
			Integer status){
		this.service.atualizarStatusPor(id, status);
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("id") 
			Integer id){
		Frete freteEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(freteEncontrado));
	}
	
	@GetMapping("/id/{id}/mex/{mes}/status/{status}")
	public ResponseEntity<?> listarPor(
			@RequestParam("id")
			Integer id,
			@RequestParam ("mes")
			Integer mes,
			@RequestParam("status")
			Integer status){
			List<Frete> fretes = service.listarPor(id, mes, status);
		return ResponseEntity.ok(converter.toJsonList(fretes));
	}
	
	@GetMapping("/cepDeOrigem/{cepDeOrigem}/cepDeDestino/{cepDeDestino}")
	public ResponseEntity<?> calcularValorFrete(
			@RequestParam("cepDeOrigem")
			String cepDeOrigem, 
			@RequestParam("cepDeDestino")
			String cepDeDestino){
			ValorDoFrete valorDoFrete = service.calcularFretePor(cepDeOrigem, cepDeDestino);
		return ResponseEntity.ok(converter.toJsonMap(valorDoFrete));
	}

	
}
