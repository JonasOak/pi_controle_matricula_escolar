package com.sollares.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sollares.model.entities.Disciplina;
import com.sollares.model.entities.Pessoa;
import com.sollares.model.repositories.DisciplinaRepository;
import com.sollares.model.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Override
	public void run(String... args) throws Exception {
		Pessoa p1 = new Pessoa("Luis", "Universal", "ES", "4002-8922", "123.456.789.10", "ladrao@gmail.com");
		Pessoa p2 = new Pessoa("Ramon", "Diadema", "ES", "9301-1235", "235.109.492.12", "pinguco@gmail.com");
		pessoaRepository.saveAll(Arrays.asList(p1, p2));
		
		Disciplina d1 = new Disciplina(0, "Matemática", 60, p1, 24);
		Disciplina d2 = new Disciplina(0, "Português", 60, p1, 20);
		Disciplina d3 = new Disciplina(0, "Inglês", 60, p2, 36);
		disciplinaRepository.saveAll(Arrays.asList(d1, d2, d3));
	}
}
