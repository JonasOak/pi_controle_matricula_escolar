/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.sollares.sollares.repositories;


import br.com.sollares.sollares.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author layla
 */
public interface PessoaRepository extends JpaRepository<Integer, Pessoa>{
    
}
