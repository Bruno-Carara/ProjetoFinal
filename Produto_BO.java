package Classes_BO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Classes_DAO.Produto_DAO;
import Classes_DTO.Categoria_DTO;
import Classes_DTO.Produto_DTO;

public class Produto_BO {
    private Produto_DAO produtoDAO;

    public Produto_BO() {
        this.produtoDAO = new Produto_DAO();
    }

    public void cadastrarProduto(Scanner scanner, List<Categoria_DTO> categorias, List<Produto_DTO> produtos) throws ParseException {
        if (categorias.isEmpty()) {
            System.out.println("Cadastro Bloqueado! Nenhuma Categoria Registrada.");
            return;
        }

        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();
        if (produtoDAO.buscarPorCodigo(codigo) != null) {
            System.out.println("Não é possível cadastrar o produto, código já existente!");
            return;
        }

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        float preco = scanner.nextFloat();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        String categoria = solicitarCategoriaProduto(scanner, categorias);

        System.out.print("Digite a validade do produto (dd/MM/yyyy): ");
        String validadeStr = scanner.nextLine();
        Date validade = new SimpleDateFormat("dd/MM/yyyy").parse(validadeStr);

        System.out.print("Digite o lote do produto: ");
        String lote = scanner.nextLine();

        Produto_DTO novoProduto = new Produto_DTO(nome, codigo, preco, quantidade, categoria, validade, lote);
        produtoDAO.salvar(novoProduto);

        System.out.println(nome + " cadastrado com sucesso.");
    }

    public void atualizarProduto(List<Produto_DTO> produtos, Scanner scanner) {
        System.out.print("Digite o código do produto a ser atualizado: ");
        String codigo = scanner.nextLine();
        Produto_DTO produtoExistente = produtoDAO.buscarPorCodigo(codigo);

        if (produtoExistente == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do produto: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo preço do produto: ");
        float novoPreco = scanner.nextFloat();

        System.out.print("Digite a nova quantidade em estoque: ");
        int novaQuantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a nova validade do produto (dd/MM/yyyy): ");
        String novaValidadeStr = scanner.nextLine();
        Date novaValidade;
        try {
            novaValidade = new SimpleDateFormat("dd/MM/yyyy").parse(novaValidadeStr);
        } catch (ParseException e) {
            System.out.println("Data inválida.");
            return;
        }

        System.out.print("Digite o novo lote do produto: ");
        String novoLote = scanner.nextLine();

        Produto_DTO produtoAtualizado = new Produto_DTO(novoNome, codigo, novoPreco, novaQuantidade, produtoExistente.getCategoria(), novaValidade, novoLote);
        produtoDAO.atualizar(produtoExistente, produtoAtualizado);

        System.out.println("Produto atualizado com sucesso.");
    }

    public void excluirProduto(Scanner scanner, List<Produto_DTO> produtos) {
        System.out.print("Digite o código do produto a ser excluído: ");
        String codigo = scanner.nextLine();
        Produto_DTO produto = produtoDAO.buscarPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtoDAO.excluir(produto);
        System.out.println("Produto removido com sucesso.");
    }

    public void gerarRelatorioProdutos(List<Produto_DTO> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\nRelatório de Produtos:");
        System.out.println("------------------------------------------------------------");
        for (Produto_DTO produto : produtos) {
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Código: " + produto.getCodigo());
            System.out.println("Categoria: " + produto.getCategoria());
            System.out.println("Lote: " + produto.getLote());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Validade: " + dateFormat.format(produto.getValidade()));
            System.out.println("------------------------------------------------------------");
        }
    }

    private String solicitarCategoriaProduto(Scanner scanner, List<Categoria_DTO> categorias) {
        while (true) {
            System.out.print("Digite a categoria do produto: ");
            String categoria = scanner.nextLine();

            for (Categoria_DTO cat : categorias) {
                if (cat.getNome().equalsIgnoreCase(categoria)) {
                    return categoria;
                }
            }

            System.out.println("Categoria não encontrada. Tente novamente.");
        }
    }
}
