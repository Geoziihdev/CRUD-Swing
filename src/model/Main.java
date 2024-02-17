package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       // Exemplo de utilização do CRUD
        inserirCliente("Geovana Santos", "geovana@example.com", "senha123");
       //buscarCliente("alice@example.com");
       // atualizarCliente("Alice", "alice@example.com", "novaSenha456");
       //deletarCliente("Geovana J Santos");
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cadastrocliente";
        String usuario = "root";
        String senha = "3240";
        return DriverManager.getConnection(url, usuario, senha);
    }

    private static void inserirCliente(String nome, String email, String password) {
        try (Connection conexao = getConnection()) {
            String sql = "INSERT INTO clientes (nome, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, email);
                statement.setString(3, password);
                int linhasAfetadas = statement.executeUpdate();
                System.out.println("Cliente inserido com sucesso.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir cliente: " + ex.getMessage());
        }
    }

    private static void buscarCliente(String email) {
        try (Connection conexao = getConnection()) {
            String sql = "SELECT * FROM clientes WHERE email = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Nome: " + resultSet.getString("nome"));
                        System.out.println("Email: " + resultSet.getString("email"));
                        System.out.println("Senha: " + resultSet.getString("password"));
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar cliente: " + ex.getMessage());
        }
    }

    private static void atualizarCliente(String nome, String email, String novaSenha) {
        try (Connection conexao = getConnection()) {
            String sql = "UPDATE clientes SET nome = ?, password = ? WHERE email = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, novaSenha);
                statement.setString(3, email);
                int linhasAfetadas = statement.executeUpdate();
                System.out.println("Cliente atualizado com sucesso.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar cliente: " + ex.getMessage());
        }
    }

    private static void deletarCliente(String nome) {
        try (Connection conexao = getConnection()) {
            String sql = "DELETE FROM clientes WHERE nome = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, nome);
                int linhasAfetadas = statement.executeUpdate();
                System.out.println("Cliente deletado com sucesso.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar cliente: " + ex.getMessage());
        }
    }
}
