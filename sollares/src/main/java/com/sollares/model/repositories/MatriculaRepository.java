package com.sollares.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

	List<Matricula> findByDisciplina(Disciplina disciplina);
}
