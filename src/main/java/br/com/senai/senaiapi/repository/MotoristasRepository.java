package br.com.senai.senaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.senaiapi.entity.Motorista;

@Repository
public interface MotoristasRepository extends JpaRepository<Motorista, Integer> {	
	
	@Query(value = "SELECT m FROM Motorista m WHERE Upper(m.nomeCompleto) LIKE Upper(:nome) ")
	List<Motorista> listarPor(String nome);
	
	@Query(value = "SELECT m FROM Motorista m WHERE m.id = :id")
	Motorista buscarPor(Integer id);
	
}
