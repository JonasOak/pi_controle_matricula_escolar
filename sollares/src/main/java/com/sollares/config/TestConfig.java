package com.sollares.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sollares.entities.Pessoa;
import com.sollares.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void run(String... args) throws Exception {
		Pessoa p1 = new Pessoa("Luis", "Universal", "ES", "4002-8922", "123.456.789.10", "ladrao@gmail.com");
		Pessoa p2 = new Pessoa("Henrique", "Universal", "ES", "4002-8922", "123.456.789.10", "ladrao@gmail.com");
		
		pessoaRepository.saveAll(Arrays.asList(p1, p2));
	}
}
