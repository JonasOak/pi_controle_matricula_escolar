/*package br.com.sollares.sollares.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "matricula")
public class MatriculaModel implements Serializable{
	
	 private static final long serialVersionUID = 1L;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idmat;

	    @ManyToOne
	    @JoinColumn(name = "id_disciplina", nullable = false)
	    private Disciplina disciplina;

	    @Temporal(TemporalType.DATE)
	    @Column(nullable = false)
	    private Date dataMatricula;

	    private double valorPago;
	    private String periodo;

	    @ManyToOne
	    @JoinColumn(name = "id_aluno", nullable = false)
	    private Pessoa aluno;

	    
	    
		public int getIdmat() {
			return idmat;
		}

		public void setIdmat(int idmat) {
			this.idmat = idmat;
		}

		public Disciplina getDisciplina() {
			return disciplina;
		}

		public void setDisciplina(Disciplina disciplina) {
			this.disciplina = disciplina;
		}

		public Date getDataMatricula() {
			return dataMatricula;
		}

		public void setDataMatricula(Date dataMatricula) {
			this.dataMatricula = dataMatricula;
		}

		public double getValorPago() {
			return valorPago;
		}

		public void setValorPago(double valorPago) {
			this.valorPago = valorPago;
		}

		public String getPeriodo() {
			return periodo;
		}

		public void setPeriodo(String periodo) {
			this.periodo = periodo;
		}

		public Pessoa getAluno() {
			return aluno;
		}

		public void setAluno(Pessoa aluno) {
			this.aluno = aluno;
		}
	    
	    

}*/
