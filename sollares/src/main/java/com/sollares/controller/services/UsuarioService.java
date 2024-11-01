package com.sollares.controller.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sollares.exception.ExcecaoServico;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}
	
	public Usuario buscarPorId(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario usuario = null;
		if (obj.isPresent()) {
			usuario = obj.get();
		}
		else {
			throw new RuntimeException("Pessoa não encontrada");
		}
		return usuario;
	}
	
	public List<Usuario> buscarPorNome(String nome) {
	    return repository.buscarNomes(nome);
	}
	
	public Usuario inserir(Usuario obj) {
		return repository.save(obj);
	}
	
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	
	public void atualizar(Usuario obj) {
		repository.save(obj);
	}

	@SuppressWarnings("unused")
	private void atualizarDados(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setCargo(obj.getCargo());
		entity.setLogin(obj.getLogin());
		entity.setSenha(obj.getSenha());
		entity.setEmail(obj.getEmail());
	}
	
    public Usuario loginUser(String login, String senha) throws ExcecaoServico {
        return repository.buscarLogin(login, senha);
    }
}
