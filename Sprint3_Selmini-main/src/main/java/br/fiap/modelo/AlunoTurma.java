package br.fiap.modelo;

public class AlunoTurma {
    private int idAluno;
    private int idTurma;

    public AlunoTurma(int idAluno, int idTurma) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
}
