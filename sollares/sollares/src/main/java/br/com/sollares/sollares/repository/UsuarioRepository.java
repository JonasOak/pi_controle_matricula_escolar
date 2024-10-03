package br.com.sollares.sollares.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sollares.sollares.model.UsuarioModel;



public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

}
