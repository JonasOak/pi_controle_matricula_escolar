package com.sollares.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sollares.model.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

}
