package com.sollares.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.repositories.DisciplinaRepository;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;

	public List<Disciplina> buscarTodos() {
		return repository.findAll();
	}

	@Transactional
	public Disciplina atualizar(Integer codigo, Disciplina obj) {
		Disciplina entity = buscarPorId(codigo);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}

	public List<Disciplina> buscarDisciplinasComProfessores() {
		return repository.buscarDisciplinasComProfessores();
	}

	public Disciplina buscarPorId(Integer codigo) {
		Optional<Disciplina> obj = repository.findById(codigo);
		Disciplina disciplina = null;
		if (obj.isPresent()) {
			disciplina = obj.get();
		} else {
			throw new RuntimeException("Disciplina não encontrada");
		}
		return disciplina;
	}

	public List<Disciplina> buscarPorNome(String nome) {
		return repository.buscarNomes(nome);
	}

	public Disciplina inserir(Disciplina obj) {
		return repository.save(obj);
	}

	public void deletar(Integer codigo) {
		buscarPorId(codigo);
		repository.deleteById(codigo);
	}

	private void atualizarDados(Disciplina entity, Disciplina obj) {
		entity.setNomeDisciplina(obj.getNomeDisciplina());
		entity.setCargaHoraria(obj.getCargaHoraria());
		entity.setLimiteAlunos(obj.getLimiteAlunos());
		entity.setProfessor(obj.getProfessor());
	}
}
