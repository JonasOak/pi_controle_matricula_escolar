package com.sollares.controller.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sollares.controller.services.PessoaService;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.PessoaRepository;

@Controller
public class PessoaResources {

	@Autowired
	private PessoaService servico;
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@GetMapping("/index")
	public String getVisualizarPortal() {
	    return "portal";
	}
	
	@GetMapping("/cadastrarPessoa")
	public String getCrudPessoa(Model model) {
		model.addAttribute("pessoa", new Pessoa());
		return "cadastrarPessoa";
	}
	
	@GetMapping("/pessoas")
	public String getVisualizarPessoas(Model model) {
		List<Pessoa> listaPessoas = servico.buscarTodos();
		model.addAttribute("listaPessoas", listaPessoas);
		model.addAttribute("pessoa", new Pessoa());
		return "listarPessoas";
	}
	
	@GetMapping("/professores")
	public String getVisualizarProfessores(Model model) {
		List<Pessoa> listaProfessores = pessoaRepository.findDistinctProfessores();
		model.addAttribute("listaProfessores", listaProfessores);
		return "listarProfessores";
	}
		
	@GetMapping("/alunos")
	public String getVisualizarAlunos(Model model) {
		List<Pessoa> listaAlunos = pessoaRepository.findDistinctAlunos();
		model.addAttribute("listaAlunos", listaAlunos);
		return "listarAlunos";
	}
	
	@GetMapping("/pessoa/buscar")
    public String buscarPessoaPorNome(@RequestParam(value = "nome", required = false) String nome, Model model) {
		List<Pessoa> listaPessoas = servico.buscarPorNome(nome);
	    model.addAttribute("listaPessoas", listaPessoas);
	    return "listarPessoas";
    }
	
    @PostMapping("/pessoa")
    public String inserir(@ModelAttribute("pessoa") Pessoa pessoa, RedirectAttributes redirectAttributes) {
    	try {
    		servico.inserir(pessoa);
    	} catch (Exception e) {
    		redirectAttributes.addFlashAttribute("ErroInserir", "Algum erro impediu de cadastrar uma pessoa.");
            return "redirect:/pessoas";
    	}
    	redirectAttributes.addFlashAttribute("PessoaCadastrada", "Pessoa cadastrada com sucesso.");
        return "redirect:/pessoas";
    }
    
    @PostMapping("/confirmarAtualizar/{id}")
    public String atualizar(@PathVariable("id") int id, @ModelAttribute("pessoa") Pessoa pessoa, RedirectAttributes redirectAttributes) {
        try {
            pessoa.setIdPessoa(id); 
            servico.atualizar(pessoa);
            redirectAttributes.addFlashAttribute("PessoaAtualizada", "Pessoa atualizada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("ErroAtualizar", "Algum erro impediu a atualização da pessoa.");
            return "redirect:/pessoas";
        }
        return "redirect:/pessoas";
    }
    
    @GetMapping("/confirmarDeletar/{id}")
    public String deletar(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes){
    	try {
            servico.deletar(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("ErroDeletar", "Não é possível deletar essa pessoa pois ela está associada como aluno ou professor.");
            return "redirect:/pessoas";
        }
    	redirectAttributes.addFlashAttribute("PessoaDeletada", "Pessoa deletada com sucesso.");
        return "redirect:/pessoas";
    }
	
	@GetMapping("/deletarPessoa/{id}")
	public String mostrarDeletrarPessoa(@PathVariable(value = "id") int id, Model model) {
		Pessoa pessoa = servico.buscarPorId(id);
		model.addAttribute("pessoa", pessoa);
		return "deletarPessoa";
	}
	
	@GetMapping("/atualizarPessoa/{id}")
	public String mostrarAtualizarPessoa(@PathVariable(value = "id") int id, Model model) {
		Pessoa pessoa = servico.buscarPorId(id);
		model.addAttribute("pessoa", pessoa);
		return "atualizarPessoa";
	}	
}
