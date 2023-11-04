package br.com.senai.modulologisticasa.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.modulologisticasa.entity.FaixaFrete;

@Repository
public interface FaixasFreteRepository extends JpaRepository<FaixaFrete, Integer>{
	
	@Query(value = 
			"SELECT ff "
			+ "FROM FaixaFrete ff "
			+ "WHERE ff.id = :id ")
	public FaixaFrete buscarPorId(Integer id);
	
	@Query(value = 
			"SELECT ff "
			+ "FROM FaixaFrete ff "
			+ "WHERE ff.valorKm = :valorKm ")
	public FaixaFrete buscarPorValorKm(BigDecimal valorKm);
	
}
