import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes_BO.Categoria_BO;
import Classes_BO.Movimentacao_BO;
import Classes_BO.Produto_BO;
import Classes_BO.Usuario_BO;
import Classes_DTO.Categoria_DTO;
import Classes_DTO.Produto_DTO;
import Classes_DTO.Usuario_DTO;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        List<Usuario_DTO> usuarios = new ArrayList<>();
        List<Produto_DTO> produtos = new ArrayList<>();
        List<Categoria_DTO> categorias = new ArrayList<>();

        Usuario_BO usuarioBO = new Usuario_BO();
        Produto_BO produtoBO = new Produto_BO();
        Categoria_BO categoriaBO = new Categoria_BO();
        Movimentacao_BO movimentacaoBO = new Movimentacao_BO();

        boolean autenticado = false;

        while (!autenticado) {
            System.out.println("=== Sistema de Almoxarifado ===");
            System.out.println("1. Fazer login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1) {
                autenticado = usuarioBO.autenticarUsuario(usuarios, scanner);
            } else if (opcao == 2) {
                usuarioBO.cadastrarUsuario(usuarios, scanner);
            } else {
                System.out.println("Opção inválida!");
            }
        }

        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar Categorias");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("4. Registrar Movimentação");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    gerenciarUsuarios(scanner, usuarioBO, usuarios);
                    break;
                case 2:
                    gerenciarCategorias(scanner, categoriaBO, categorias);
                    break;
                case 3:
                    gerenciarProdutos(scanner, produtoBO, produtos, categorias);
                    break;
                case 4:
                	 gerenciarMovimentacao(scanner, movimentacaoBO, produtos);
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void gerenciarUsuarios(Scanner scanner, Usuario_BO usuarioBO, List<Usuario_DTO> usuarios) {
        System.out.println("\n=== Gerenciar Usuários ===");
        System.out.println("1. Listar usuários");
        System.out.println("2. Atualizar usuário");
        System.out.println("3. Remover usuário");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                usuarioBO.listarUsuarios(usuarios);
                break;
            case 2:
                usuarioBO.atualizarUsuario(usuarios, scanner);
                break;
            case 3:
                usuarioBO.removerUsuario(usuarios, scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void gerenciarCategorias(Scanner scanner, Categoria_BO categoriaBO, List<Categoria_DTO> categorias) {
        System.out.println("\n=== Gerenciar Categorias ===");
        System.out.println("1. Listar categorias");
        System.out.println("2. Cadastrar nova categoria");
        System.out.println("3. Atualizar categoria");
        System.out.println("4. Remover categoria");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                categoriaBO.listarCategorias();
                break;
            case 2:
                categoriaBO.cadastrarCategoria(scanner);
                break;
            case 3:
                categoriaBO.atualizarCategoria(scanner);
                break;
            case 4:
                categoriaBO.excluirCategoria(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void gerenciarProdutos(Scanner scanner, Produto_BO produtoBO, List<Produto_DTO> produtos, List<Categoria_DTO> categorias) throws ParseException {
        System.out.println("\n=== Gerenciar Produtos ===");
        System.out.println("1. Listar produtos");
        System.out.println("2. Cadastrar novo produto");
        System.out.println("3. Atualizar produto");
        System.out.println("4. Remover produto");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                produtoBO.gerarRelatorioProdutos(produtos);
                break;
            case 2:
                produtoBO.cadastrarProduto(scanner, categorias, produtos);
                break;
            case 3:
                produtoBO.atualizarProduto(produtos, scanner);
                break;
            case 4:
                produtoBO.excluirProduto(scanner, produtos);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    private static void gerenciarMovimentacao(Scanner scanner, Movimentacao_BO movimentacaoBO, List<Produto_DTO> produtos) {
        System.out.println("\n=== Registrar Movimentação ===");
        System.out.println("1. Registrar entrada de produto");
        System.out.println("2. Registrar saída de produto");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                movimentacaoBO.registrarEntrada(scanner, produtos);
                break;
            case 2:
                movimentacaoBO.registrarSaida(scanner, produtos);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}
