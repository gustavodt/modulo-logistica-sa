package br.com.senai.modulologisticasa.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.senai.modulologisticasa.entity.FaixaFrete;
import br.com.senai.modulologisticasa.repository.FaixasFreteRepository;

class FaixaFreteServiceImplTest {

    @Mock
    private FaixasFreteRepository repository;

    @InjectMocks
    private FaixaFreteServiceImpl service;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testBuscarPorIdExistente() {
    	 Integer id = 1;

         FaixaFrete freteEncontrado = new FaixaFrete();
         freteEncontrado.setId(id);

         when(repository.buscarPorId(eq(id))).thenReturn(freteEncontrado);

         FaixaFrete result = service.buscarPor(id);

         assertEquals(freteEncontrado, result);
    }

    @Test
    public void testBuscarPorIdNaoExistente() {
    	Integer id = 1;

        FaixaFrete freteEncontrado = new FaixaFrete();
        freteEncontrado.setId(id);

        when(repository.buscarPorId(eq(id))).thenReturn(freteEncontrado);

        FaixaFrete result = service.buscarPor(id);

        assertEquals(freteEncontrado, result);
    }
}
