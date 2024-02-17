package model;

import java.util.Scanner;

public class CadastroCliente {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Cliente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chamar o método para cadastrar o cliente
        cadastrarCliente(nome, email, senha);

        scanner.close();
    }

    private static void cadastrarCliente(String nome, String email, String senha) {
        // Aqui você implementa a lógica para cadastrar o cliente no banco de dados
        // Por exemplo, você pode usar JDBC para se conectar a um banco de dados e executar uma instrução SQL INSERT
        // Neste exemplo, apenas imprimimos os dados cadastrados
        System.out.println("Cliente cadastrado com sucesso:");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        // Não imprima a senha, isso é apenas um exemplo de segurança
    }
}
