package com.sollares.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> buscarTodos() {
		return repository.findAll();
	}
	
	public Pessoa buscarPorId(Integer id) {
		Optional<Pessoa> obj = repository.findById(id);
		Pessoa pessoa = null;
		if (obj.isPresent()) {
			pessoa = obj.get();
		}
		else {
			throw new RuntimeException("Pessoa não encontrada");
		}
		return pessoa;
	}
	
	public Pessoa inserir(Pessoa obj) {
		return repository.save(obj);
	}
	
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	public Pessoa atualizar(Integer id, Pessoa obj) {
		Pessoa entity = repository.getReferenceById(id);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}

	private void atualizarDados(Pessoa entity, Pessoa obj) {
		entity.setNomePessoa(obj.getNomePessoa());
		entity.setEndereco(obj.getEndereco());
		entity.setUf(obj.getUf());
		entity.setTelefone(obj.getTelefone());
		entity.setCpf(obj.getCpf());
		entity.setEmail(obj.getEmail());
	}
}
	