package br.fiap.util;

import br.fiap.conexao.Conexao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ScriptExecutor {

    public static void executeScript(String filePath) {
        System.out.println("--- Executando script de inicialização ---");
        try (Connection conn = Conexao.conectar();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            if (conn == null) {
                System.out.println("Não foi possível conectar ao banco de dados.");
                return;
            }

            StringBuilder scriptContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                scriptContent.append(line).append("\n");
            }

            String[] commands = scriptContent.toString().split(";");

            try (Statement stmt = conn.createStatement()) {
                for (String command : commands) {
                    String trimmedCommand = command.trim();
                    if (!trimmedCommand.isEmpty()) {
                        try {
                            stmt.execute(trimmedCommand);
                        } catch (SQLException e) {
                            System.err.println("Erro ao executar comando: " + trimmedCommand);
                            System.err.println("Erro: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Script executado com sucesso!");

        } catch (IOException | SQLException e) {
            System.err.println("Erro durante a execução do script: " + e.getMessage());
        }
    }
}
