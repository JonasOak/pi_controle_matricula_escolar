/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sollares.sollares.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author layla
 */

@Entity
@Table(name = "matricula")
public class Matricula {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMat;

    @ManyToOne
    @JoinColumn(name = "disciplinaId")
    private Disciplina disciplina;

    @Column(name = "dataMatricula")
    private Date dataMatricula;

    @Column(name = "valorPago")
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "alunoId")
    private Pessoa aluno;

    private String periodo;

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
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

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Pessoa getAluno() {
        return aluno;
    }

    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
    
}
