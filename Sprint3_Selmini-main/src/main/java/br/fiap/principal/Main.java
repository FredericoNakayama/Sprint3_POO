package br.fiap.principal;

import br.fiap.dao.AlunoDAO;
import br.fiap.dao.AlunoTurmaDAO;
import br.fiap.dao.ProfessorDAO;
import br.fiap.dao.TurmaDAO;
import br.fiap.modelo.Aluno;
import br.fiap.modelo.AlunoTurma;
import br.fiap.modelo.Professor;
import br.fiap.modelo.Turma;
import br.fiap.util.ScriptExecutor;
import java.net.URL;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Tente carregar o arquivo usando o ClassLoader, que é mais robusto
        URL resourceUrl = ScriptExecutor.class.getClassLoader().getResource("Script_Sprint3_Selmini.sql");
        if (resourceUrl != null) {
            ScriptExecutor.executeScript(resourceUrl.getFile());
        } else {
            System.out.println("Caminho do script não encontrado. Verifique se o arquivo está em src/main/resources.");
        }


        // Instancia as classes DAO
        ProfessorDAO professorDAO = new ProfessorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        AlunoTurmaDAO alunoTurmaDAO = new AlunoTurmaDAO();

        // 1. Inserção de dados
        System.out.println("--- 1. Inserindo dados de Professor, Aluno e Turma ---");

        // Inserção de Professores
        Professor professor1 = new Professor(0, "João da Silva", "Matemática", Date.valueOf("2020-01-15"));
        professorDAO.inserirProfessor(professor1);

        Professor professor2 = new Professor(0, "Maria Oliveira", "Português", Date.valueOf("2021-08-22"));
        professorDAO.inserirProfessor(professor2);

        // Inserção de Alunos
        Aluno aluno1 = new Aluno(0, "Maria Souza", Date.valueOf("2005-03-20"), "maria.souza@email.com");
        alunoDAO.inserirAluno(aluno1);

        Aluno aluno2 = new Aluno(0, "Pedro Santos", Date.valueOf("2006-07-15"), "pedro.santos@email.com");
        alunoDAO.inserirAluno(aluno2);

        // Inserção de Turmas
        Turma turma1 = new Turma("Engenharia de Software", 2025, 1);
        turmaDAO.inserirTurma(turma1);

        Turma turma2 = new Turma("Análise e Desenvolvimento de Sistemas", 2025, 2);
        turmaDAO.inserirTurma(turma2);

        // Inserção da relação Aluno-Turma
        AlunoTurma alunoTurma1 = new AlunoTurma(1, 1);
        alunoTurmaDAO.inserirAlunoTurma(alunoTurma1);

        AlunoTurma alunoTurma2 = new AlunoTurma(2, 2);
        alunoTurmaDAO.inserirAlunoTurma(alunoTurma2);


        // 2. Listagem e Pesquisa de dados
        System.out.println("\n--- 2. Listando e Pesquisando dados ---");

        // Listagem de Professores
        System.out.println("-> Professores:");
        List<Professor> professores = professorDAO.buscarTodosProfessores();
        for (Professor p : professores) {
            System.out.println("ID: " + p.getIdProfessor() + ", Nome: " + p.getNome() + ", Especialidade: " + p.getEspecialidade());
        }

        // Listagem de Alunos
        System.out.println("\n-> Alunos:");
        List<Aluno> alunos = alunoDAO.buscarTodosAlunos();
        for (Aluno a : alunos) {
            System.out.println("ID: " + a.getIdAluno() + ", Nome: " + a.getNome() + ", Email: " + a.getEmail());
        }

        // Pesquisa de uma Turma específica
        System.out.println("\n-> Pesquisa de Turma:");
        Turma turmaEncontrada = turmaDAO.buscarPorId(1);
        if (turmaEncontrada != null) {
            System.out.println("Turma encontrada: " + turmaEncontrada.getNomeTurma() + ", Ano Letivo: " + turmaEncontrada.getAnoLetivo());
        }


        // 3. Atualização de dados
        System.out.println("\n--- 3. Atualizando dados ---");

        // Atualização de Professor
        Professor professorParaAtualizar = new Professor(1, "João da Silva Junior", "Matemática Avançada", Date.valueOf("2020-01-15"));
        if (professorDAO.atualizarProfessor(professorParaAtualizar)) {
            System.out.println("Professor atualizado com sucesso!");
        }

        // Atualização de Aluno
        Aluno alunoParaAtualizar = new Aluno(1, "Maria Souza", Date.valueOf("2005-03-20"), "maria.souza.nova@email.com");
        if (alunoDAO.atualizarAluno(alunoParaAtualizar)) {
            System.out.println("Aluno atualizado com sucesso!");
        }

        // Atualização de Turma
        Turma turmaParaAtualizar = new Turma(1, "Engenharia de Software (2025)", 2025, 1);
        if (turmaDAO.atualizarTurma(turmaParaAtualizar)) {
            System.out.println("Turma atualizada com sucesso!");
        }


        // 4. Exclusão de dados
        System.out.println("\n--- 4. Excluindo dados ---");

        // Remoção da relação Aluno-Turma primeiro para evitar erro de integridade
        AlunoTurma alunoTurmaRemover1 = new AlunoTurma(2, 2);
        if (alunoTurmaDAO.removerAlunoTurma(alunoTurmaRemover1)) {
            System.out.println("Relação Aluno-Turma com aluno ID 2 removida com sucesso!");
        }

        AlunoTurma alunoTurmaRemover2 = new AlunoTurma(1, 1);
        if (alunoTurmaDAO.removerAlunoTurma(alunoTurmaRemover2)) {
            System.out.println("Relação Aluno-Turma com aluno ID 1 removida com sucesso!");
        }

        // Agora podemos remover os registros principais
        int idAlunoParaRemover = 2;
        if (alunoDAO.removerAluno(idAlunoParaRemover)) {
            System.out.println("Aluno com ID " + idAlunoParaRemover + " removido com sucesso!");
        }

        int idTurmaParaRemover = 2;
        if (turmaDAO.removerTurma(idTurmaParaRemover)) {
            System.out.println("Turma com ID " + idTurmaParaRemover + " removida com sucesso!");
        }

        int idProfessorParaRemover = 2;
        if (professorDAO.removerProfessor(idProfessorParaRemover)) {
            System.out.println("Professor com ID " + idProfessorParaRemover + " removido com sucesso!");
        }

        System.out.println("\nProjeto finalizado!");
    }
}