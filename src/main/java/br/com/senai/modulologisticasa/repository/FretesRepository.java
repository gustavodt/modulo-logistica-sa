package br.com.senai.modulologisticasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.modulologisticasa.entity.Frete;

@Repository
public interface FretesRepository extends JpaRepository<Frete, Integer>{
	
	@Query(value = 
			"SELECT f "
			+ "FROM Frete f "
			+ "WHERE EXTRACT(YEAR FROM f.dataMovimento) = :ano "
			+ "AND EXTRACT(MONTH FROM f.dataMovimento) = :mes "
			+ "ORDER BY f.idEntregador ",
			countQuery = 
					"SELECT f "
					+ "FROM Frete f "
					+ "WHERE EXTRACT(YEAR, f.dataMovimento) = :ano "
					+ "AND EXTRACT(MONTH, f.dataMovimento) = :mes ")
	public List<Frete> listarPor(Integer ano, Integer mes);
	
	@Query(value = 
			"SELECT f "
			+ "FROM Frete f "
			+ "WHERE f.status = :status "
			+ "ORDER BY f.id",
			countQuery = 
					"SELECT f "
					+ "FROM Frete f "
					+ "WHERE f.status = :status ")
	public List<Frete> listarPorStatus(Integer status);
	
	@Query(value = 
			"SELECT f "
			+ "FROM Frete f "
			+ "WHERE f.id = :id ")
	public Frete buscarPorId(Integer id);
	
	@Query(value = 
			"SELECT f "
			+ "FROM Frete f "
			+ "WHERE f.idPedido = :idPedido ")
	public Frete buscarPorIdPedido(Integer idPedido);
	
}
