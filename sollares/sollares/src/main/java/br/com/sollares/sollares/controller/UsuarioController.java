package br.com.sollares.sollares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.sollares.sollares.model.UsuarioModel;
import br.com.sollares.sollares.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("cadastroUsuario")
	public ModelAndView cadastrar(UsuarioModel usuario) {
		ModelAndView mv = new ModelAndView("administrativo/ususarios/cadastro");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
}
