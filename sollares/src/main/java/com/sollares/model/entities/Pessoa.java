package com.sollares.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPessoa;
	
	@Column(name = "nome_pessoa")
	private String nomePessoa;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "email")
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aluno")
	private List<Matricula> matriculas = new ArrayList<>();
	
	@JsonIgnore // cortar looping infinito na requisição
	@OneToMany(mappedBy = "professor")
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	public Pessoa() {
	}
	
	public Pessoa(String nomePessoa, String endereco, String uf, String telefone, String cpf, String email) {
		super();
		this.nomePessoa = nomePessoa;
		this.endereco = endereco;
		this.uf = uf;
		this.telefone = telefone;
		this.cpf = cpf;
		this.email = email;
	}


	public Pessoa(int idPessoa, String nomePessoa, String endereco, String uf, String telefone, String cpf, String email) {
		super();
		this.idPessoa = idPessoa;
		this.nomePessoa = nomePessoa;
		this.endereco = endereco;
		this.uf = uf;
		this.telefone = telefone;
		this.cpf = cpf;
		this.email = email;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return idPessoa == other.idPessoa;
	}
	
	

}
