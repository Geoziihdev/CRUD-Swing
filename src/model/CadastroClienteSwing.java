package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroClienteSwing {
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField; // Adicionando campo para senha

    public CadastroClienteSwing() {
        JFrame frame = new JFrame("Cadastro de Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5)); // Modificando o layout para incluir o campo de senha
        frame.getContentPane().add(panel);

        JLabel nomeLabel = new JLabel("Nome:");
        panel.add(nomeLabel);

        nomeField = new JTextField();
        panel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);

        emailField = new JTextField();
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:"); // Adicionando label para senha
        panel.add(senhaLabel);

        senhaField = new JPasswordField(); // Usando JPasswordField para senha
        panel.add(senhaField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        panel.add(cadastrarButton);

        frame.pack();
        frame.setVisible(true);
    }

    private void cadastrarCliente() {
        String url = "jdbc:mysql://localhost:3306/cadastrocliente";
        String usuario = "root";
        String senha = "3240";

        String nome = nomeField.getText();
        String email = emailField.getText();
        String password = new String(senhaField.getPassword()); // Obtendo a senha como uma String

        String sql = "INSERT INTO clientes (nome, email, password) VALUES (?, ?, ?)"; // Corrigindo o nome da coluna para 'password'
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            nomeField.setText("");
            emailField.setText("");
            senhaField.setText(""); // Limpando o campo de senha após a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroClienteSwing();
            }
        });
    }
}
