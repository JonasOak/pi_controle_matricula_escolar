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
import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.DisciplinaRepository;

import jakarta.servlet.http.HttpSession;


@Controller
@SessionAttributes("usuarioLogado")
public class DisciplinaResources {

	@Autowired
	private DisciplinaService servico;
	
	@Autowired
    private DisciplinaRepository disciplinaRepository;
	
	
	@ModelAttribute("usuarioLogado")
    public Usuario getUsuarioLogado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogado");
    }
	
	
	@GetMapping("/disciplinas")
    public String listarTodos(HttpSession session, Model model) {
	    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	    if (usuarioLogado == null) {
	        model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
	        return "redirect:/index";
	    }
        List<Disciplina> listaDisciplinas = servico.buscarDisciplinasComProfessores();
        model.addAttribute("disciplinas", listaDisciplinas);
        return "listarDisciplinas"; 
    }

    @GetMapping("/buscar")
    public String buscarDisciplinaPorNome(@RequestParam(value = "nome", required = false) String nome, Model model) {
        List<Disciplina> disciplinas = servico.buscarPorNome(nome);
        model.addAttribute("disciplinas", disciplinas); 
        return "listarDisciplinas";
    }

    @GetMapping("/disciplinaCadastrar")
    public String cadastrarDisciplina(HttpSession session, Model model) {
	    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	    if (usuarioLogado == null) {
	        model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
	        return "redirect:/index";
	    }
        model.addAttribute("disciplina", new Disciplina());
        List<Pessoa> listaProfessores = disciplinaRepository.buscarPessoas();
        model.addAttribute("listaProfessores", listaProfessores);
        return "disciplinaCadastrar";
    }

    @PostMapping("/disciplina")
    public String inserir(@ModelAttribute("disciplina") Disciplina disciplina, RedirectAttributes redirectAttributes) {
        try {
            servico.inserir(disciplina);
            redirectAttributes.addFlashAttribute("DisciplinaCadastrada", "Disciplina cadastrada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("ErroInserir", "Erro ao cadastrar disciplina.");
            return "redirect:/disciplinas";
        }
        return "redirect:/disciplinas";
    }

 
	 
	 @GetMapping("/disciplinaAtualizar/{codigo}")
	 public String mostrarAtualizarDisciplina(@PathVariable("codigo") int codigo, Model model) {
	     Disciplina disciplina = servico.buscarPorId(codigo);
	     List<Pessoa> listaProfessores = disciplinaRepository.buscarPessoas();
	     model.addAttribute("disciplina", disciplina);
	     model.addAttribute("listaProfessores", listaProfessores);
	     return "disciplinaAtualizar";
	 }

	 @PostMapping("/confirmarAtualizarDisciplina/{codigo}")
	 public String atualizar(@PathVariable("codigo") int codigo, @ModelAttribute("disciplina") Disciplina disciplina, RedirectAttributes redirectAttributes) {
	     try {
	         servico.atualizar(codigo, disciplina); 
	         redirectAttributes.addFlashAttribute("DisciplinaAtualizada", "Disciplina atualizada com sucesso.");
	     } catch (Exception e) {
	         redirectAttributes.addFlashAttribute("ErroAtualizar", "Erro ao atualizar disciplina.");
	     }
	     return "redirect:/disciplinas";
	 }

    
	 @GetMapping("/disciplinaDeletar/{codigo}")
	 public String mostrarDeletarDisciplina(@PathVariable("codigo") int codigo, Model model) {
	     Disciplina disciplina = servico.buscarPorId(codigo);
	     model.addAttribute("disciplina", disciplina);
	     return "disciplinaDeletar";
	 }


	 @GetMapping("/confirmarDeletarDisciplina/{codigo}")
	 public String deletar(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
	     try {
	         servico.deletar(codigo);
	         redirectAttributes.addFlashAttribute("DisciplinaDeletada", "Disciplina deletada com sucesso.");
	     } catch (Exception e) {
	    	 System.out.println("Erro ao deletar disciplina: " + e.getMessage());
	         redirectAttributes.addFlashAttribute("ErroDeletar", "Não é possível deletar essa disciplina pois há um ou mais alunos matriculados nela.");
	     }
	     return "redirect:/disciplinas";
	 }
}
