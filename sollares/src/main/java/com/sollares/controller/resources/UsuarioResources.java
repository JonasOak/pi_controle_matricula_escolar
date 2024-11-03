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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sollares.controller.services.UsuarioService;
import com.sollares.exception.ExcecaoServico;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioResources {
	
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService servico;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping("/index") // método que conversmos 
    public ModelAndView index(HttpSession session, Model model, @RequestParam(value = "page", required = false) String page) {
        ModelAndView mv = new ModelAndView();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            if (page != null) {
                switch (page) {
                    case "portal":
                        mv.setViewName("portal");
                        break;
                    case "outraPagina1":
                        mv.setViewName("outraPagina1"); 
                        break;
                    case "outraPagina2":
                        mv.setViewName("outraPagina2");
                        break;
                    default:
                        mv.setViewName("portal");
                        break;
                }
            } else {
                mv.setViewName("portal"); 
            }
        } else {
            mv.addObject("usuario", new Usuario());
            model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
            mv.setViewName("login");
        }

        return mv;
    }
    
    
	/*
	 * @GetMapping("/index") public ModelAndView index(HttpSession session, Model
	 * model) { ModelAndView mv = new ModelAndView(); Usuario usuarioLogado =
	 * (Usuario) session.getAttribute("usuarioLogado");
	 * 
	 * if (usuarioLogado != null) { mv.setViewName("portal"); } else {
	 * mv.addObject("usuario", new Usuario()); model.addAttribute("msgFaltaLogin",
	 * "Por favor, faça login para acessar o portal."); mv.setViewName("login"); }
	 * 
	 * return mv; }
	 */


    @GetMapping("/registrar")
    public ModelAndView getCadastrarUsuario() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("registrar");
        return mv;
    }

    @PostMapping("salvarUsuario")
    public ModelAndView cadastrar(Usuario usuario) throws Exception {
        ModelAndView mv = new ModelAndView();
        servico.inserir(usuario);
        mv.setViewName("redirect:/");
        return mv;
    }

    @ModelAttribute
    public void addUsuarioLogadoToModel(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado != null) {
            model.addAttribute("usuarioLogado", usuarioLogado);
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String senha, HttpSession session, Model model) throws ExcecaoServico {
        Usuario usuarioLogado = servico.loginUser(login, senha);

        if (usuarioLogado != null) {
            session.setAttribute("usuarioLogado", usuarioLogado);
            return "redirect:/index";
        } else {
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("msgLoginErrado", "Usuário ou senha inválidos.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
    
	@GetMapping("/usuario/buscar")
    public String buscarPessoaPorNome(@RequestParam(value = "nome", required = false) String nome, Model model) {
		List<Usuario> listaUsuarios = servico.buscarPorNome(nome);
	    model.addAttribute("listaUsuarios", listaUsuarios);
	    return "listarUsuarios";
    }
    
	@GetMapping("/usuarioCadastrar")
	public String getCrudUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarioCadastrar";
	}
	
	@GetMapping("/usuarios")
	public String getVisualizarUsuarios(Model model) {
		List<Usuario> listaUsuarios = servico.buscarTodos();
		model.addAttribute("listaUsuarios", listaUsuarios);
		model.addAttribute("usuario", new Usuario());
		return "listarUsuarios";
	}
	
    @PostMapping("/usuario")
    public String inserir(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
    	try {
    		servico.inserir(usuario);
    	} catch (Exception e) {
    		redirectAttributes.addFlashAttribute("ErroInserir", "Algum erro impediu de cadastramento.");
            return "redirect:/usuarios";
    	}
    	redirectAttributes.addFlashAttribute("UsuarioCadastrado", "cadastrado feito com sucesso.");
        return "redirect:/usuarios";
    }
    
    @PostMapping("/confirmarAtualizarUser/{id}")
    public String atualizar(@PathVariable("id") int id, @ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuario.setIdUsuario(id); 
            servico.atualizar(usuario);
            redirectAttributes.addFlashAttribute("UsuarioAtualizado", "Atualização feita com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("ErroAtualizar", "Algum erro impediu a atualização.");
            return "redirect:/usuarios";
        }
        return "redirect:/usuarios";
    }
    
    @PostMapping("/confirmarDeletarUser/{id}")
    public String deletar(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes){
    	try {
            servico.deletar(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("ErroDeletar", "Não é possível deletar essa pessoa pois ela está associada como aluno ou professor.");
            return "redirect:/usuarios";
        }
    	redirectAttributes.addFlashAttribute("UsuarioDeletado", "Exclusão com sucesso.");
        return "redirect:/usuarios";
    }
	
	@GetMapping("/usuarioDeletar/{id}")
	public String mostrarDeletrarPessoa(@PathVariable(value = "id") int id, Model model) {
		Usuario usuario = servico.buscarPorId(id);
		model.addAttribute("usuario", usuario);
		return "usuarioDeletar";
	}
	
	@GetMapping("/usuarioAtualizar/{id}")
	public String mostrarAtualizarPessoa(@PathVariable(value = "id") int id, Model model) {
		Usuario usuario = servico.buscarPorId(id);
		model.addAttribute("usuario", usuario);
		return "usuarioAtualizar";
	}	
}
