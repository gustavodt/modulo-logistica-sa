package br.com.senai.modulologisticasa.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.modulologisticasa.dto.GoogleMatrix;
import br.com.senai.modulologisticasa.repository.FretesRepository;
import br.com.senai.modulologisticasa.service.FreteService;
import br.com.senai.modulologisticasa.service.GoogleMatrixService;

public class GoogleMatrixImpl implements GoogleMatrixService{

	@Autowired
	private FretesRepository fretesRepository;
	
	@Override
	public GoogleMatrix buscarDistancia(
			Integer origem,
			Integer destino) {
		// TODO Auto-generated method stub
		return null;
	}

}
