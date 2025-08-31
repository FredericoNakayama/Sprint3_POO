package br.fiap.dao;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlunoDAO() {
        this.conexao = Conexao.conectar();
    }

    public boolean inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO s3selmini_alunos (nome, data_nascimento, email) VALUES (?, ?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setDate(2, aluno.getDataNascimento());
            ps.setString(3, aluno.getEmail());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno\n" + e);
        }
        return false;
    }

    public Aluno consultarAluno(int idAluno) {
        String sql = "SELECT * FROM s3selmini_alunos WHERE id_aluno = ?";
        Aluno aluno = null;
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idAluno);
            rs = ps.executeQuery();
            if (rs.next()) {
                aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar aluno\n" + e);
        }
        return aluno;
    }

    public List<Aluno> buscarTodosAlunos() {
        String sql = "SELECT * FROM s3selmini_alunos ORDER BY nome";
        List<Aluno> listaAlunos = new ArrayList<>();
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("email")
                );
                listaAlunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos\n" + e);
        }
        return listaAlunos;
    }

    public boolean atualizarAluno(Aluno aluno) {
        String sql = "UPDATE s3selmini_alunos SET nome = ?, data_nascimento = ?, email = ? WHERE id_aluno = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setDate(2, aluno.getDataNascimento());
            ps.setString(3, aluno.getEmail());
            ps.setInt(4, aluno.getIdAluno());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno\n" + e);
        }
        return false;
    }

    public boolean removerAluno(int idAluno) {
        String sql = "DELETE FROM s3selmini_alunos WHERE id_aluno = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idAluno);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover aluno\n" + e);
        }
        return false;
    }
}
