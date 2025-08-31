package br.fiap.modelo;

public class Turma {
     private int idTurma;
     private String nomeTurma;
     private int anoLetivo;
     private int idProfessor;

     public Turma(String nomeTurma, int anoLetivo, int idProfessor) {
         this.nomeTurma = nomeTurma;
         this.anoLetivo = anoLetivo;
         this.idProfessor = idProfessor;
     }

     public Turma(int idTurma, String nomeTurma, int anoLetivo, int idProfessor) {
         this.idTurma = idTurma;
         this.nomeTurma = nomeTurma;
         this.anoLetivo = anoLetivo;
         this.idProfessor = idProfessor;
     }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}
