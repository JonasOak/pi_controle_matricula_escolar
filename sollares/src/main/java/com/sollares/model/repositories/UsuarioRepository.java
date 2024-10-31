package com.sollares.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sollares.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.login = :login and u.senha = :senha")
	public Usuario buscarLogin(String login, String senha);
	
}
