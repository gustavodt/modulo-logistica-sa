package br.com.senai.modulologisticasa.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.repository.FretesRepository;

public class FreteServiceImplTest {
	
	@Mock
    private FretesRepository repository;

    @InjectMocks
    private FreteServiceImpl service;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAtualizarStatusPor() {
        Integer id = 1;
        Integer status = 2;

        Frete freteEncontrado = new Frete();
        freteEncontrado.setId(id);
        freteEncontrado.setStatus(1);

        when(repository.buscarPorId(eq(id))).thenReturn(freteEncontrado);

        service.atualizarStatusPor(id, status);

        verify(repository, times(1)).buscarPorId(eq(id));
        verify(repository, times(1)).atualizarPor(eq(id), eq(status));
    }

    @Test()
    public void testAtualizarStatusPorMesmoStatus() {
        try {
        	Integer id = 1;
            Integer status = 1;

            Frete freteEncontrado = new Frete();
            freteEncontrado.setId(id);
            freteEncontrado.setStatus(status);

            when(repository.buscarPorId(eq(id))).thenReturn(freteEncontrado);

            service.atualizarStatusPor(id, status);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
    }

    @Test()
    public void testAtualizarStatusPorFreteNaoEncontrado() {
        try {
        	Integer id = 1;
            Integer status = 2;

            when(repository.buscarPorId(eq(id))).thenReturn(null);

            service.atualizarStatusPor(id, status);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testBuscarPor() {
        Integer id = 1;

        Frete freteEncontrado = new Frete();
        freteEncontrado.setId(id);

        when(repository.buscarPorId(eq(id))).thenReturn(freteEncontrado);

        Frete result = service.buscarPor(id);

        assertEquals(freteEncontrado, result);
    }

    @Test()
    public void testBuscarPorFreteNaoEncontrado() {
        try {
        	Integer id = 1;

            when(repository.buscarPorId(eq(id))).thenReturn(null);

            service.buscarPor(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testListarPor() {
        Integer ano = 1;
        Integer mes = 2;
        Integer status = 3;

        List<Frete> freteList = new ArrayList<>();
        when(repository.listarPor(eq(ano), eq(mes), eq(status))).thenReturn(freteList);

        List<Frete> result = service.listarPor(ano, mes, status);

        assertEquals(freteList, result);
        verify(repository, times(1)).listarPor(eq(ano), eq(mes), eq(status));
    }
	
}
