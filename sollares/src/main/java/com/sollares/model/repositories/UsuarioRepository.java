package com.sollares.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sollares.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
