package br.fiap.dao;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProfessorDAO() {
        this.conexao = Conexao.conectar();
    }

    public boolean inserirProfessor(Professor professor) {
        String sql = "INSERT INTO s3selmini_professores (nome, especialidade, data_contratacao) VALUES (?, ?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEspecialidade());
            ps.setDate(3, professor.getDataContrato());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir professor\n" + e);
        }
        return false;
    }

    public Professor buscarPorId(int idProfessor) {
        String sql = "SELECT * FROM s3selmini_professores WHERE id_professor = ?";
        Professor professor = null;
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idProfessor);
            rs = ps.executeQuery();
            if (rs.next()) {
                professor = new Professor(
                        rs.getInt("id_professor"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getDate("data_contratacao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor\n" + e);
        }
        return professor;
    }

    public List<Professor> buscarTodosProfessores() {
        String sql = "SELECT * FROM s3selmini_professores ORDER BY nome";
        List<Professor> listaProfessores = new ArrayList<>();
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("id_professor"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getDate("data_contratacao")
                );
                listaProfessores.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professores\n" + e);
        }
        return listaProfessores;
    }

    public boolean atualizarProfessor(Professor professor) {
        String sql = "UPDATE s3selmini_professores SET nome = ?, especialidade = ?, data_contratacao = ? WHERE id_professor = ?";
        try {
            ps=conexao.prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEspecialidade());
            ps.setDate(3, professor.getDataContrato());
            ps.setInt(4, professor.getIdProfessor());
            ps.execute();
            return true;
        }
        catch (SQLException e){
            System.out.println("Erro ao atualizar professor\n" + e);
        }
        return false;
    }

    public boolean removerProfessor(int idProfessor) {
        String sql = "DELETE FROM s3selmini_professores WHERE id_professor = ?";
        try {
            ps=conexao.prepareStatement(sql);
            ps.setInt(1, idProfessor);
            ps.execute();
            return true;
        }
        catch (SQLException e){
            System.out.println("Erro ao remover professor\n" + e);
        }
        return false;
    }
}
