package com.sollares.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sollares.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
