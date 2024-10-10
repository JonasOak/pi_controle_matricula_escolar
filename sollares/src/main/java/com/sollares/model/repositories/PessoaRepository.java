package com.sollares.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sollares.model.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
