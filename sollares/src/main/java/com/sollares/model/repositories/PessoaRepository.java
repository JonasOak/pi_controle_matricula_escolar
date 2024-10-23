package com.sollares.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sollares.model.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	@Query("SELECT p FROM Pessoa p JOIN Disciplina d ON p.idPessoa = d.professor.idPessoa")
	List<Pessoa> findDistinctProfessores();
	
	@Query("SELECT p FROM Pessoa p JOIN Matricula d ON p.idPessoa = d.aluno.idPessoa")
	List<Pessoa> findDistinctAlunos();
	
}
