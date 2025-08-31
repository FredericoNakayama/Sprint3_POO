package br.fiap.modelo;
import java.sql.Date;

public class Professor {
    private int idProfessor;
    private String nome;
    private String especialidade;
    private Date dataContrato;

    public Professor(int idProfessor, String nome, String especialidade, Date dataContrato) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.especialidade = especialidade;
        this.dataContrato = dataContrato;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }
}
