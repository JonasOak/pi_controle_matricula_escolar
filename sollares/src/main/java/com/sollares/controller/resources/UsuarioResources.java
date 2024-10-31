package com.sollares.controller.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sollares.controller.services.UsuarioService;
import com.sollares.exception.ExcecaoServico;
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
	
	@GetMapping("/index")
	public ModelAndView index(HttpSession session, Model model) {
	    ModelAndView mv = new ModelAndView();
	    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

	    if (usuarioLogado != null) {
	        mv.setViewName("portal");
	        mv.addObject("usuarioLogado", usuarioLogado);
	    } else {
	        mv.addObject("usuario", new Usuario());
	        model.addAttribute("msgFaltaLogin", "Por favor, faça login para acessar o portal.");
	        mv.setViewName("login");
	    }

	    return mv;
	}
	
	@GetMapping("/registrar")
	public ModelAndView getCadastrarUsuario() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("registrar");
		return mv;
	}
	
	@PostMapping("salvarUsuario")
	public ModelAndView inserir(Usuario usuario) throws Exception {
		ModelAndView mv = new ModelAndView();
		servico.inserir(usuario);
		mv.setViewName("redirect:/");
		return mv;
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

	/*
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> list = servico.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
		Usuario obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario obj) {
		obj = servico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario obj) {
		obj = servico.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	*/
	
}
