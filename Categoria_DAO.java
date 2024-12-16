package Classes_DAO;

import java.util.ArrayList;
import java.util.List;

import Classes_DTO.Categoria_DTO;

public class Categoria_DAO {
    private List<Categoria_DTO> categorias;

    public Categoria_DAO() {
        this.categorias = new ArrayList<>();
    }

    public void salvar(Categoria_DTO categoria) {
        categorias.add(categoria);
    }

    public Categoria_DTO buscarPorNome(String nome) {
        for (Categoria_DTO categoria : categorias) {
            if (categoria.getNome().equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        return null;
    }

    public void atualizar(Categoria_DTO categoriaAntiga, Categoria_DTO categoriaNova) {
        categorias.remove(categoriaAntiga);
        categorias.add(categoriaNova);
    }

    public void excluir(Categoria_DTO categoria) {
        categorias.remove(categoria);
    }

    public List<Categoria_DTO> listarTodos() {
        return categorias;
    }
}
