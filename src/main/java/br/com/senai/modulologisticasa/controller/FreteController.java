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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.modulologisticasa.dto.ValorDoFrete;
import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import br.com.senai.modulologisticasa.service.EntregadorService;
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
	
	@Autowired
	@Qualifier("entregadorServiceProxy")
	EntregadorService entregadorService;
	
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
	
	@Transactional
	@PatchMapping("/id-entregador/{id-entregador}/id-pedido/{id-pedido}/aceito")
	public ResponseEntity<?> atualizarParaAceitoPor(
			@PathVariable("id-entregador")
			Integer idDoEntregador,
			@PathVariable("id-pedido")
			Integer idDoPedido){
		this.service.aceitarParaEntregaPor(idDoEntregador, idDoPedido);
		return ResponseEntity.ok().build();
	}
	
	@Transactional
	@PatchMapping("/id/{id}/idPedido/{idPedido}/emailEntregador/{emailEntregador}/aceitoParaEntrega")
	public ResponseEntity<?> atualizarStatusParaAceitoParaEntrega(
			@PathVariable("id")
			Integer id,
			@PathVariable("idPedido")
			Integer idPedido,
			@PathVariable("emailEntregador")
			String emailEntregador){
		Integer idEntregador = this.entregadorService.buscarIdEntregadorPor(emailEntregador);
		this.service.atualizarStatusPor(id, Status.ACEITO_PARA_ENTREGA, idPedido, idEntregador);
		return ResponseEntity.ok().build();
		
	}
	
	@Transactional
	@PatchMapping("/id/{id}/idPedido/{idPedido}/emailEntregador/{emailEntregador}/entregue")
	public ResponseEntity<?> atualizarStatusParaEntregue(
			@PathVariable("id")
			Integer id,
			@PathVariable("idPedido")
			Integer idPedido,
			@PathVariable("emailEntregador")
			String emailEntregador){
		Integer idEntregador = this.entregadorService.buscarIdEntregadorPor(emailEntregador);
		this.service.atualizarStatusPor(id, Status.ENTREGUE, idPedido, idEntregador);
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("id") 
			Integer id){
		Frete freteEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(freteEncontrado));
	}
	
	@GetMapping("/ano/{ano}/mes/{mes}")
	public ResponseEntity<?> listarPor(
			@PathVariable("ano")
			Integer ano,
			@PathVariable("mes")
			Integer mes){
			List<Frete> fretes = service.listarPor(ano, mes);
		return ResponseEntity.ok(converter.toJsonList(fretes));
	}
	
	@GetMapping("/cepDeOrigem/{cepDeOrigem}/cepDeDestino/{cepDeDestino}")
	public ResponseEntity<?> calcularValorFrete(
			@PathVariable("cepDeOrigem")
			String cepDeOrigem, 
			@PathVariable("cepDeDestino")
			String cepDeDestino){
			ValorDoFrete valorDoFrete = service.calcularFretePor(cepDeOrigem, cepDeDestino);
		return ResponseEntity.ok(converter.toJsonMap(valorDoFrete));
	}

	
}
