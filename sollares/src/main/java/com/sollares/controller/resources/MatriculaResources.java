package com.sollares.controller.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sollares.controller.services.DisciplinaService;
import com.sollares.controller.services.MatriculaService;
import com.sollares.controller.services.PessoaService;
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
	private PessoaService servicoPessoa;
	
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
        model.addAttribute("listaMatriculas", listaDisciplinas);
        return "listarMatriculas"; 
    }
	
    @GetMapping("/matricula/buscar")
    public String buscarMatriculasPorNome(@RequestParam(value = "nome", required = false) String nome, Model model) {
        List<Matricula> matricula = servico.buscarPorNome(nome);
        model.addAttribute("listaMatriculas", matricula); 
        return "listarMatriculas";
    }
    
    @GetMapping("/matriculaCadastrar")
    public String cadastrarMatricula(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
            return "redirect:/index";
        }
        model.addAttribute("matricula", new Matricula());
        List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
        model.addAttribute("listaDisciplinas", listaDisciplinas);
        List<Pessoa> listaPessoas = matriculaRepository.buscarPessoas();
        model.addAttribute("listaPessoas", listaPessoas);
        
        return "matriculaCadastrar";
    }
    
    @PostMapping("/matricula")
    public String inserir(@ModelAttribute("matricula") Matricula matricula, RedirectAttributes redirectAttributes, Model model) {
        try {
            servico.inserir(matricula);
            redirectAttributes.addFlashAttribute("successMessage", "Matrícula cadastrada com sucesso.");
            return "redirect:/matriculas"; // Redireciona para a página de listagem de matrículas.
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao cadastrar matrícula: " + e.getMessage());
            return "teste"; // Retorna à página do formulário em caso de erro.
        }
        
        
    }
    
    //método atualizar 
    
    @GetMapping("/matricula/editar/{id}")
    public String editarMatricula(@PathVariable("id") Integer id, Model model) {
        Matricula matricula = servico.buscarPorId(id);
        List<Disciplina> listaDisciplinas = servicoDisciplina.buscarTodos();
        List<Pessoa> listaPessoas = matriculaRepository.buscarPessoas();

        model.addAttribute("matricula", matricula);
        model.addAttribute("listaDisciplinas", listaDisciplinas);
        model.addAttribute("listaPessoas", listaPessoas);

        return "matriculaAtualizar"; 
    }
    
    @PostMapping("/matricula/atualizar/{id}")
    public String atualizarMatricula(@PathVariable("id") Integer id, @ModelAttribute("matricula") Matricula matriculaAtualizada, 
                                     RedirectAttributes redirectAttributes, Model model) {
        try {
            servico.atualizar(id, matriculaAtualizada);
            redirectAttributes.addFlashAttribute("successMessage", "Matrícula atualizada com sucesso.");
            return "redirect:/matriculas";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao atualizar matrícula: " + e.getMessage());
            return "matriculaAtualizar"; 
        }
    }
    
    
    
    // Deletar não funciona tem que fazer um confirma, fiquei cansada jonas rsss
    
    
    
    @GetMapping("/matricula/deletar/{id}")
    public String deletarMatricula(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            servico.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Matrícula deletada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar matrícula: " + e.getMessage());
        }
        return "redirect:/matriculas";
    }

    
    
	
	/*
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
