package com.sollares.controller.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sollares.controller.services.DisciplinaService;
import com.sollares.model.entities.Disciplina;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaResources {

	@Autowired
	private DisciplinaService servico;
	
	@GetMapping
	public ResponseEntity<List<Disciplina>> buscarTodos() {
		List<Disciplina> list = servico.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Disciplina> buscarPorId(@PathVariable Integer id) {
		Disciplina obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Disciplina> inserir(@RequestBody Disciplina obj) {
		obj = servico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Disciplina> atualizar(@PathVariable Integer id, @RequestBody Disciplina obj) {
		obj = servico.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
