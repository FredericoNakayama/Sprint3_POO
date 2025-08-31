package br.fiap.dao;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.AlunoTurma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoTurmaDAO {
    private Connection conexao;
    private PreparedStatement ps;

    public AlunoTurmaDAO() {
        this.conexao = Conexao.conectar();
    }

    public boolean inserirAlunoTurma(AlunoTurma alunoTurma) {
        String sql = "INSERT INTO s3selmini_alunos_turmas (id_aluno, id_turma) VALUES (?, ?)";
        try {
            ps=conexao.prepareStatement(sql);
            ps.setInt(1, alunoTurma.getIdAluno());
            ps.setInt(2, alunoTurma.getIdTurma());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno na turma\n" + e);
        }
        return false;
    }

    public boolean removerAlunoTurma(AlunoTurma alunoTurma) {
        String sql = "DELETE FROM s3selmini_alunos_turmas WHERE id_aluno = ? AND id_turma = ?";
        try {
            ps=conexao.prepareStatement(sql);
            ps.setInt(1, alunoTurma.getIdAluno());
            ps.setInt(2, alunoTurma.getIdTurma());
            ps.execute();
            return true;
        }
        catch (SQLException e){
            System.out.println("Erro ao remover aluno da turma\n" + e);
        }
        return false;
    }
}
