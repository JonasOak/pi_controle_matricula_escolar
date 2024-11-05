package com.sollares.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
	
	@Query("SELECT p FROM Pessoa p WHERE LOWER(p.nomePessoa) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Pessoa> buscarNomes(@Param("nome") String nome);

	List<Matricula> findByDisciplina(Disciplina disciplina);
	
    @Query("SELECT m FROM Matricula m JOIN FETCH m.disciplina")
    List<Matricula> buscarDisciplinasComProfessores();
	
    @Query("SELECT m FROM Matricula m JOIN FETCH m.aluno")
    List<Matricula> buscarMatriculasComAlunos();
    
    @Query("SELECT p FROM Pessoa p")
    List<Pessoa> buscarPessoas();
}
