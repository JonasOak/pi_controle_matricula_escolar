package com.sollares.controller.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sollares.controller.services.PessoaService;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.PessoaRepository;

@Controller
public class PessoaResources {

	@Autowired
	private PessoaService servico;
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@GetMapping("/professores")
	public String getVisualizarProfessores(Model model) {
		List<Pessoa> listaProfessores = pessoaRepository.findDistinctProfessores();
		model.addAttribute("listaProfessores", listaProfessores);
		return "listTodosProf";
	}
		
	@GetMapping("/alunos")
	public String getVisualizarAlunos(Model model) {
		List<Pessoa> listaAlunos = pessoaRepository.findDistinctAlunos();
		model.addAttribute("listaAlunos", listaAlunos);
		return "listTodosAlun";
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> buscarTodos() {
		List<Pessoa> list = servico.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Integer id) {
		Pessoa obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa obj) {
		obj = servico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPessoa()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id, @RequestBody Pessoa obj) {
		obj = servico.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
