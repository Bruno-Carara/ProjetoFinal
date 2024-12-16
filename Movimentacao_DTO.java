package Classes_DTO;

import java.util.Date;

public class Movimentacao_DTO {
    private Date data;
    private int quantidade;
    private String responsavel;
    private String tipo;
    private String lote;

    public Movimentacao_DTO(Date data, int quantidade, String responsavel, String tipo, String lote) {
        this.data = data;
        this.quantidade = quantidade;
        this.responsavel = responsavel;
        this.tipo = tipo;
        this.lote = lote;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
