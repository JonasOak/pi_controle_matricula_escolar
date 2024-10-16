package com.sollares.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sollares.exception.LimiteException;
import com.sollares.model.entities.Matricula;
import com.sollares.model.repositories.DisciplinaRepository;
import com.sollares.model.repositories.MatriculaRepository;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository repository;
	
	@Autowired
	private DisciplinaRepository disciplinarepository;
	
	public List<Matricula> buscarTodos() {
		return repository.findAll();
	}
	
	public Matricula buscarPorId(Integer id) {
		Optional<Matricula> obj = repository.findById(id);
		return obj.get();
	}
	
	public Matricula inserir(Matricula obj) {
		List<Matricula> matriculas = repository.findByDisciplina(obj.getDisciplina());
	    int limiteAlunos = obj.getDisciplina().getLimiteAlunos();
	    
	    if (matriculas.size() >= limiteAlunos) {
	        throw new LimiteException("O limite de alunos para esta disciplina já foi atingido.");
	    }
	    return repository.save(obj);
	}
	
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	public Matricula atualizar(Integer id, Matricula obj) {
		Matricula entity = repository.getReferenceById(id);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}

	private void atualizarDados(Matricula entity, Matricula obj) {
		entity.setAluno(obj.getAluno());
		entity.setDisciplina(obj.getDisciplina());
		entity.setPeriodo(obj.getPeriodo());
		entity.setDataMatricula(obj.getDataMatricula());
		entity.setValorPago(obj.getValorPago());
	}
}
	