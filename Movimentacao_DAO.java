package Classes_DAO;

import java.util.ArrayList;
import java.util.List;

import Classes_DTO.Movimentacao_DTO;

public class Movimentacao_DAO {
    private List<Movimentacao_DTO> movimentacoes;

    public Movimentacao_DAO() {
        this.movimentacoes = new ArrayList<>();
    }

    public void salvar(Movimentacao_DTO movimentacao) {
        movimentacoes.add(movimentacao);
    }

    public List<Movimentacao_DTO> listarTodas() {
        return movimentacoes;
    }
}
