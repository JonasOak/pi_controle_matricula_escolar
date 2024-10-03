/*package br.com.sollares.sollares.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplina")
public class DisciplinaModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private String nomeDisciplina;

    private int cargaHoraria;
    private int limiteAlunos;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Pessoa professor;
    
    
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getLimiteAlunos() {
		return limiteAlunos;
	}

	public void setLimiteAlunos(int limiteAlunos) {
		this.limiteAlunos = limiteAlunos;
	}

	public Pessoa getProfessor() {
		return professor;
	}

	public void setProfessor(Pessoa professor) {
		this.professor = professor;
	}
    
    

}*/
