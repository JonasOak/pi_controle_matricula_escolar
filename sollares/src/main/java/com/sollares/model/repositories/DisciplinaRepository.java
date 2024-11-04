package com.sollares.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Pessoa;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

	@Query("SELECT d FROM Disciplina d WHERE LOWER(d.nomeDisciplina) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Disciplina> buscarNomes(@Param("nome") String nome);
    
    @Query("SELECT d FROM Disciplina d JOIN FETCH d.professor")
    List<Disciplina> buscarDisciplinasComProfessores();
    
    @Query("SELECT p FROM Pessoa p")
    List<Pessoa> buscarPessoas(); // Layla, este método é para listar todas as Pessoas para deixar como Professor
                                  // sendo chave estrangeira da tabela Pessoa

}
