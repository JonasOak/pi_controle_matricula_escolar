package com.sollares.controller.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sollares.exception.LimiteException;
import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.DisciplinaRepository;
import com.sollares.model.repositories.MatriculaRepository;
import com.sollares.model.repositories.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository repository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Matricula> buscarTodos() {
		return repository.findAll();
	}

	public List<Matricula> buscarPorNome(String nome) {
		return repository.buscarNomes(nome);
	}

	@Transactional
	public Matricula atualizar(Integer codigo, Matricula obj) {
		Matricula entity = buscarPorId(codigo);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}

	public List<Matricula> buscarDisciplinasComProfessores() {
		return repository.buscarDisciplinasComProfessores();
	}

	public List<Matricula> buscarMatriculaComAlunos() {
		return repository.buscarMatriculasComAlunos();
	}

	public Matricula buscarPorId(Integer codigo) {
		Optional<Matricula> obj = repository.findById(codigo);
		Matricula matricula = null;
		if (obj.isPresent()) {
			matricula = obj.get();
		} else {
			throw new RuntimeException("Matricula não encontrada");
		}
		return matricula;
	}

	public Matricula inserir(Matricula obj) {
		List<Matricula> matriculas = repository.findByDisciplina(obj.getDisciplina());
		int limiteAlunos = obj.getDisciplina().getLimiteAlunos();

		if (matriculas.size() >= limiteAlunos) {
			throw new LimiteException("Limite de alunos atingido.");
		}
		return repository.save(obj);
	}

	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	public BigDecimal consultarFaturamento(Disciplina disciplina) {
	    return repository.consultarFaturamento(disciplina);
	}


	private void atualizarDados(Matricula entity, Matricula obj) {
		entity.setAluno(obj.getAluno());
		entity.setDisciplina(obj.getDisciplina());
		entity.setPeriodo(obj.getPeriodo());
		entity.setDataMatricula(obj.getDataMatricula());
		entity.setValorPago(obj.getValorPago());
	}
	
	

}
