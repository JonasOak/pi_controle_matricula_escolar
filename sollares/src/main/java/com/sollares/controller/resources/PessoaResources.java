package com.sollares.controller.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sollares.controller.services.PessoaService;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.PessoaRepository;

@Controller
public class PessoaResources {

	@Autowired
	private PessoaService servico;
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@GetMapping("/")
	public String getVisualizarPortal(Model model) {
	    return "portal";
	}
	
	@GetMapping("/manterPessoa")
	public String getCrudPessoa(Model model) {
		model.addAttribute("pessoa", new Pessoa());
		return "pessoa";
	}
	
	@GetMapping("/pessoas")
	public String getVisualizarPessoas(Model model) {
		List<Pessoa> listaPessoas = servico.buscarTodos();
		model.addAttribute("listaPessoas", listaPessoas);
		return "listTodasPessoas";
	}
	
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
	
	@GetMapping("/pessoa/{id}")
    public String buscarPessoa(@PathVariable(value = "id") long id,Model model) {
        Pessoa pessoa = servico.buscarPorId((int) id);
        return "pessoaProfessor";
    }
	
    @PostMapping("/pessoa")
    public String inserir(@ModelAttribute("pessoa") Pessoa pessoa) {
        servico.inserir(pessoa);
        return "redirect:/pessoas";
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
