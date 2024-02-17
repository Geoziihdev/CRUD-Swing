package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {

    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/cadastrocliente";
        String usuario = "root";
        String senha = "3240";

        // Conectar ao banco de dados
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão bem-sucedida!");

            // Aqui você pode realizar operações no banco de dados, como consultas SQL
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
