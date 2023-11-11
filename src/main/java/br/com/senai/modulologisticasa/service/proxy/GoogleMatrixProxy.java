package br.com.senai.modulologisticasa.service.proxy;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.modulologisticasa.dto.GoogleMatrix;
import br.com.senai.modulologisticasa.service.GoogleMatrixService;

@Service 
public class GoogleMatrixProxy implements GoogleMatrixService{

	@Autowired
	private ProducerTemplate getGoogleMatrix;

	@Override
	public GoogleMatrix buscarDistancia(Integer origem, Integer destino) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
