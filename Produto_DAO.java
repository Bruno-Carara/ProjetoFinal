package Classes_DAO;

import java.util.ArrayList;
import java.util.List;

import Classes_DTO.Produto_DTO;

public class Produto_DAO {
    private List<Produto_DTO> produtos;

    public Produto_DAO() {
        this.produtos = new ArrayList<>();
    }

    public void salvar(Produto_DTO produto) {
        produtos.add(produto);
    }

    public Produto_DTO buscarPorCodigo(String codigo) {
        for (Produto_DTO produto : produtos) {
            if (produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }

    public void atualizar(Produto_DTO produtoAntigo, Produto_DTO produtoAtualizado) {
        produtos.remove(produtoAntigo);
        produtos.add(produtoAtualizado);
    }

    public void excluir(Produto_DTO produto) {
        produtos.remove(produto);
    }

    public List<Produto_DTO> listarTodos() {
        return produtos;
    }
}