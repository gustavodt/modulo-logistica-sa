package br.com.senai.modulologisticasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.modulologisticasa.entity.Frete;
import br.com.senai.modulologisticasa.entity.enuns.Status;
import jakarta.transaction.Transactional;

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
			+ "WHERE f.idEntregador = :idEntregador ")
	public Frete buscarPorIdPedido(Integer id);
	
	
	@Modifying
	@Transactional
	@Query(value = 
			"UPDATE Frete f "
			+ "SET f.status = :status, "
			+ "f.idPedido = :idPedido, "
			+ "f.idEntregador = :idEntregador "
			+ "WHERE f.id = :id")
	public void atualizarPor(Integer id, Status status, Integer idPedido, Integer idEntregador);
	
}
