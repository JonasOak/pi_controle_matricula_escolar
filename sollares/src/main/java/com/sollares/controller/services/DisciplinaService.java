package com.sollares.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;
	
	public List<Disciplina> buscarTodos() {
		return repository.findAll();
	}
	
	public Disciplina buscarPorId(Integer id) {
		Optional<Disciplina> obj = repository.findById(id);
		return obj.get();
	}
	
	public Disciplina inserir(Disciplina obj) {
		return repository.save(obj);
	}
	
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	public Disciplina atualizar(Integer id, Disciplina obj) {
		Disciplina entity = repository.getReferenceById(id);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}

	private void atualizarDados(Disciplina entity, Disciplina obj) {
		entity.setNomeDisciplina(obj.getNomeDisciplina());
		entity.setCargaHoraria(obj.getCargaHoraria());
		entity.setLimiteAlunos(obj.getLimiteAlunos());
		entity.setProfessor(obj.getProfessor());
	}
}
	