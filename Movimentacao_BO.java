package Classes_BO;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Classes_DAO.Movimentacao_DAO;
import Classes_DTO.Movimentacao_DTO;
import Classes_DTO.Produto_DTO;

public class Movimentacao_BO {
    private Movimentacao_DAO movimentacaoDAO;

    public Movimentacao_BO() {
        this.movimentacaoDAO = new Movimentacao_DAO();
    }

    public void registrarEntrada(Scanner scanner, List<Produto_DTO> produtos) {
        System.out.print("Digite o código do produto para registrar a entrada: ");
        String codigoProduto = scanner.nextLine().trim();
        Produto_DTO produtoEntrada = encontrarProdutoPorCodigo(produtos, codigoProduto);

        if (produtoEntrada == null) {
            System.out.println("Produto não encontrado! Verifique o código digitado.");
            return;
        }

        int quantidadeEntrada = 0;
        while (true) {
            System.out.print("Digite a quantidade de entrada: ");
            try {
                quantidadeEntrada = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha pendente
                if (quantidadeEntrada <= 0) {
                    System.out.println("Erro: A quantidade deve ser um número positivo.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro positivo.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }

        System.out.print("Digite o responsável pela entrada: ");
        String responsavelEntrada = scanner.nextLine().trim();

        System.out.print("Digite o lote: ");
        String loteEntrada = scanner.nextLine().trim();

        Movimentacao_DTO novaMovimentacao = new Movimentacao_DTO(new Date(), quantidadeEntrada, responsavelEntrada, "Entrada", loteEntrada);
        movimentacaoDAO.salvar(novaMovimentacao);

        produtoEntrada.setQuantidade(produtoEntrada.getQuantidade() + quantidadeEntrada);
        System.out.println("Produto atualizado: " + produtoEntrada);
    }

    public void registrarSaida(Scanner scanner, List<Produto_DTO> produtos) {
        System.out.print("Digite o código do produto para registrar a saída: ");
        String codigoProduto = scanner.nextLine();

        Produto_DTO produto = encontrarProdutoPorCodigo(produtos, codigoProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("Digite a quantidade de saída: ");
        int quantidadeSaida = scanner.nextInt();
        scanner.nextLine();

        if (produto.getQuantidade() < quantidadeSaida) {
            System.out.println("Erro: Estoque insuficiente para saída.");
            return;
        }

        System.out.print("Digite o responsável pela saída: ");
        String responsavelSaida = scanner.nextLine();

        System.out.print("Digite o lote: ");
        String loteSaida = scanner.nextLine();

        Movimentacao_DTO novaMovimentacao = new Movimentacao_DTO(new Date(), quantidadeSaida, responsavelSaida, "Saída", loteSaida);
        movimentacaoDAO.salvar(novaMovimentacao);

        produto.setQuantidade(produto.getQuantidade() - quantidadeSaida);
        System.out.println("Saída registrada: " + novaMovimentacao);
    }

    private Produto_DTO encontrarProdutoPorCodigo(List<Produto_DTO> produtos, String codigo) {
        if (produtos == null || codigo == null) {
            return null; // Retorna null se a lista ou o código for nulo
        }
        for (Produto_DTO produto : produtos) {
            if (codigo.equals(produto.getCodigo())) { // Comparação segura para evitar NullPointerException
                return produto;
            }
        }
        return null; // Retorna null se nenhum produto for encontrado
    }
}
