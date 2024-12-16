package Classes_BO;

import java.util.Scanner;

import Classes_DAO.Categoria_DAO;
import Classes_DTO.Categoria_DTO;

public class Categoria_BO {
    private Categoria_DAO categoriaDAO;

    public Categoria_BO() {
        this.categoriaDAO = new Categoria_DAO();
    }

    public void cadastrarCategoria(Scanner scanner) {
        System.out.println("Digite o nome da nova categoria: ");
        String nome = scanner.nextLine();
        if (categoriaDAO.buscarPorNome(nome) != null) {
            System.out.println("Categoria já cadastrada!");
            return;
        }

        System.out.print("Digite a descrição da categoria: ");
        String descricao = scanner.nextLine();
        Categoria_DTO novaCategoria = new Categoria_DTO(nome, descricao);
        categoriaDAO.salvar(novaCategoria);
        System.out.println("Categoria " + nome + " cadastrada com sucesso.");
    }

    public void atualizarCategoria(Scanner scanner) {
        System.out.println("Digite o nome da categoria que será atualizada: ");
        String nome = scanner.nextLine();
        Categoria_DTO categoriaExistente = categoriaDAO.buscarPorNome(nome);
        if (categoriaExistente == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.println("Digite o novo nome da categoria: ");
        String novoNome = scanner.nextLine();
        System.out.println("Digite a nova descrição da categoria: ");
        String novaDescricao = scanner.nextLine();

        Categoria_DTO categoriaAtualizada = new Categoria_DTO(novoNome, novaDescricao);
        categoriaDAO.atualizar(categoriaExistente, categoriaAtualizada);
        System.out.println("Categoria " + novoNome + " atualizada com sucesso.");
    }

    public void excluirCategoria(Scanner scanner) {
        System.out.println("Digite o nome da categoria que será excluída: ");
        String nome = scanner.nextLine();
        Categoria_DTO categoriaExistente = categoriaDAO.buscarPorNome(nome);
        if (categoriaExistente == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        categoriaDAO.excluir(categoriaExistente);
        System.out.println("Categoria " + nome + " removida com sucesso.");
    }

    public void listarCategorias() {
        System.out.println("Categorias registradas:");
        for (Categoria_DTO categoria : categoriaDAO.listarTodos()) {
            System.out.println("----------------------------");
            System.out.println("Nome: " + categoria.getNome());
            System.out.println("Descrição: " + categoria.getDescricao());
            System.out.println("----------------------------");
        }
    }
}
