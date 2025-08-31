package br.fiap.dao;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.Turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public TurmaDAO() {
        this.conexao = Conexao.conectar();
    }

    public boolean inserirTurma(Turma turma) {
        String sql = "INSERT INTO s3selmini_turmas (nome_turma, ano_letivo, id_professor) VALUES (?, ?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, turma.getNomeTurma());
            ps.setInt(2, turma.getAnoLetivo());
            ps.setInt(3, turma.getIdProfessor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir turma\n" + e);
        }
        return false;
    }

    public Turma buscarPorId(int idTurma) {
        String sql = "SELECT * FROM s3selmini_turmas WHERE id_turma = ?";
        Turma turma = null;
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idTurma);
            rs = ps.executeQuery();
            if (rs.next()) {
                turma = new Turma(
                        rs.getInt("id_turma"),
                        rs.getString("nome_turma"),
                        rs.getInt("ano_letivo"),
                        rs.getInt("id_professor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar turma\n" + e);
        }
        return turma;
    }

    public List<Turma> buscarTodos() {
        String sql = "SELECT * FROM s3selmini_turmas ORDER BY nome_turma";
        List<Turma> listaTurmas = new ArrayList<>();
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma(
                        rs.getInt("id_turma"),
                        rs.getString("nome_turma"),
                        rs.getInt("ano_letivo"),
                        rs.getInt("id_professor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar turmas\n" + e);
        }
        return listaTurmas;
    }

    public boolean atualizarTurma(Turma turma) {
        String sql = "UPDATE s3selmini_turmas SET nome_turma = ?, ano_letivo = ?, id_professor = ? WHERE id_turma = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, turma.getNomeTurma());
            ps.setInt(2, turma.getAnoLetivo());
            ps.setInt(3, turma.getIdProfessor());
            ps.setInt(4, turma.getIdTurma());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar turma\n" + e);
        }
        return false;
    }

    public boolean removerTurma(int idTurma) {
        String sql = "DELETE FROM s3selmini_turmas WHERE id_turma = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idTurma);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover turma\n" + e);
        }
        return false;
    }
}
