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

import com.sollares.controller.services.MatriculaService;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.MatriculaRepository;

@Controller
public class MatriculaResources {

	@Autowired
	private MatriculaService servico;
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	/*
	@GetMapping("/manterMatricula")
	public String getCrudMatricula(Model model) {
		model.addAttribute("matricula", new Matricula());
		return "matricula";
	}
	
	@GetMapping("/matriculas")
	public String getVisualizarMatriculas(Model model) {
		List<Matricula> listaMatriculas= servico.buscarTodos();
		model.addAttribute("listaMatriculas", listaMatriculas);
		return "listTodasMatriculas";
	}
	
	@GetMapping
	public ResponseEntity<List<Matricula>> buscarTodos() {
		List<Matricula> list = servico.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Matricula> buscarPorId(@PathVariable Integer id) {
		Matricula obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Matricula> inserir(@RequestBody Matricula obj) {
		obj = servico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMat()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	/*
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Matricula> atualizar(@PathVariable Integer id, @RequestBody Matricula obj) {
		obj = servico.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	*/
	
}
