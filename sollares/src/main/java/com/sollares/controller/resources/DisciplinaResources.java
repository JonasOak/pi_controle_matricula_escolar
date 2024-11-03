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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.PessoaRepository;
import com.sollares.controller.services.DisciplinaService;
import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Pessoa;

import jakarta.servlet.http.HttpSession;


@Controller
public class DisciplinaResources {

	@Autowired
	private DisciplinaService servico;
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	
	@ModelAttribute("usuarioLogado")
    public Usuario getUsuarioLogado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogado");
    }
	
	
	@GetMapping("/disciplinas")
    public String listarTodos(Model model) {
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
    public String cadastrarDisciplina(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        List<Pessoa> listaProfessores = pessoaRepository.findDistinctProfessores();
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
	     model.addAttribute("disciplina", disciplina);
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


	 @PostMapping("/confirmarDeletarDisciplina/{codigo}")
	 public String deletar(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
	     try {
	         servico.deletar(codigo);
	         redirectAttributes.addFlashAttribute("DisciplinaDeletada", "Disciplina deletada com sucesso.");
	     } catch (Exception e) {
	         redirectAttributes.addFlashAttribute("ErroDeletar", "Erro ao deletar disciplina.");
	     }
	     return "redirect:/disciplinas";
	 }


	
	/*
	 * @GetMapping public ResponseEntity<List<Disciplina>> buscarTodos() {
	 * List<Disciplina> list = servico.buscarTodos(); return
	 * ResponseEntity.ok().body(list); }
	 * 
	 * @GetMapping(value = "/{id}") public ResponseEntity<Disciplina>
	 * buscarPorId(@PathVariable Integer id) { Disciplina obj =
	 * servico.buscarPorId(id); return ResponseEntity.ok().body(obj); }
	 */
	  
	/*
	 * @PostMapping public ResponseEntity<Disciplina> inserir(@RequestBody
	 * Disciplina obj) { obj = servico.inserir(obj); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (obj.getCodigo()).toUri(); return ResponseEntity.created(uri).body(obj); }
	 * 
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<Void>
	 * deletar(@PathVariable Integer id) { servico.deletar(id); return
	 * ResponseEntity.noContent().build(); }
	 * 
	 * @PutMapping(value = "/{id}") public ResponseEntity<Disciplina>
	 * atualizar(@PathVariable Integer id, @RequestBody Disciplina obj) { obj =
	 * servico.atualizar(id, obj); return ResponseEntity.ok().body(obj); }
	 */
}
