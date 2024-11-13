package com.sollares.model.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
	
	@Query("SELECT d FROM Disciplina d JOIN FETCH d.professor p WHERE p.idPessoa = :professorId")
    List<Disciplina> buscarDisciplinasPorProfessor(@Param("professorId") Integer professorId);
	
	@Query("SELECT m.disciplina FROM Matricula m WHERE m.aluno.idPessoa = :alunoId")
    List<Disciplina> buscarDisciplinasPorAluno(@Param("alunoId") Integer alunoId);
	
	@Query("SELECT m FROM Matricula m WHERE LOWER(m.aluno.nomePessoa) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Matricula> buscarNomes(@Param("nome") String nome);

	List<Matricula> findByDisciplina(Disciplina disciplina);
	
    @Query("SELECT m FROM Matricula m JOIN FETCH m.disciplina")
    List<Matricula> buscarDisciplinasComProfessores();
	
    @Query("SELECT m FROM Matricula m JOIN FETCH m.aluno")
    List<Matricula> buscarMatriculasComAlunos();
    
    @Query("SELECT p FROM Pessoa p")
    List<Pessoa> buscarPessoas();
    
   @Query("SELECT SUM(m.valorPago) FROM Matricula m WHERE m.disciplina = :disciplina") 
    BigDecimal consultarFaturamento(@Param("disciplina") Disciplina disciplina);


}
