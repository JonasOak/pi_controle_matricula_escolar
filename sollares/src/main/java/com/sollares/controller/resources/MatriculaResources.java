package com.sollares.controller.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sollares.controller.services.DisciplinaService;
import com.sollares.controller.services.MatriculaService;
import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.MatriculaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("usuarioLogado")
public class MatriculaResources {

	@Autowired
	private MatriculaService servico;
	
	@Autowired
	private DisciplinaService servicoDisciplina;
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@ModelAttribute("usuarioLogado")
    public Usuario getUsuarioLogado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogado");
    }
	
	@GetMapping("/matriculas")
    public String listarTodos(HttpSession session, Model model) {
	    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	    if (usuarioLogado == null) {
	        model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
	        return "redirect:/index";
	    }
        List<Matricula> listaAlunos = servico.buscarMatriculaComAlunos();
        List<Matricula> listaDisciplinas = servico.buscarDisciplinasComProfessores();
        model.addAttribute("listaAlunos", listaAlunos);
        model.addAttribute("listaDisciplinas", listaDisciplinas);
        return "listarMatriculas"; 
    }
	
	
	/*
	@GetMapping("/manterMatricula")
	public String getCrudMatricula(Model model) {
		model.addAttribute("matricula", new Matricula());
		return "matricula";
	}
	*/
	/*
	@GetMapping("/matriculas")
	public String getVisualizarMatriculas(Model model) {
		List<Matricula> listaMatriculas= servico.buscarTodos();
		model.addAttribute("listaMatriculas", listaMatriculas);
		return "listarMatriculas";
	}
	
	 @GetMapping("/matriculaAtualizar/{id}")
	 public String mostrarAtualizarMatricula(@PathVariable("id") int id, Model model) {
		 Matricula matricula = servico.buscarPorId(id);
	     List<Pessoa> listaProfessores = matriculaRepository.buscarPessoas();
	     model.addAttribute("matricula", matricula);
	     model.addAttribute("listaProfessores", listaProfessores);
	     return "matriculaAtualizar";
	 }
	*/
	/*
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
