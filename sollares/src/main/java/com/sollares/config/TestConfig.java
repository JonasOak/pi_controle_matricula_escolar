package com.sollares.config;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Matricula;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.entities.Usuario;
import com.sollares.model.repositories.DisciplinaRepository;
import com.sollares.model.repositories.MatriculaRepository;
import com.sollares.model.repositories.PessoaRepository;
import com.sollares.model.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Pessoa p1 = new Pessoa("Luis", "Universal", "ES", "4002-8922", "123.456.789.10", "ladrao@gmail.com");
		Pessoa p2 = new Pessoa("Ramon", "Diadema", "SP", "9301-1235", "235.109.492.12", "pinguco@gmail.com");
		Pessoa p3 = new Pessoa("Jonas", "Salvador", "BA", "1230-1435", "235.109.492.12", "jonas@gmail.com");
		pessoaRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Disciplina d1 = new Disciplina(0, "Matemática", 60, p1, 24);
		Disciplina d2 = new Disciplina(0, "Português", 60, p1, 20);
		Disciplina d3 = new Disciplina(0, "Inglês", 60, p2, 3);
		disciplinaRepository.saveAll(Arrays.asList(d1, d2, d3));
		
		Matricula m1 = new Matricula(0, d1, p3, sdf.parse("14-10-2024"), new BigDecimal("600"), "1° período");
		Matricula m2 = new Matricula(0, d3, p2, sdf.parse("14-10-2024"), new BigDecimal("300"), "2° período");
		Matricula m3 = new Matricula(0, d3, p3, sdf.parse("14-10-2024"), new BigDecimal("600"), "1° período");
		Matricula m4 = new Matricula(0, d3, p2, sdf.parse("14-10-2024"), new BigDecimal("300"), "2° período");
		matriculaRepository.saveAll(Arrays.asList(m1, m2, m3, m4));
		
		Usuario u1 = new Usuario(0, "Jonas", "Administrador", "jonasOak", "123456", "emaildejonas@gmail.com");
		Usuario u2 = new Usuario(0, "SouUmAluno", "Aluno", "alunoTeste", "123456", "emaildealuno@gmail.com");
		Usuario u3 = new Usuario(0, "SouUmProfessor", "Professor", "professorTeste", "123456", "emaildeprofessor@gmail.com");
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
	}
}
