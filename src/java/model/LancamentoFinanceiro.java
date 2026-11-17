package model;

public class LancamentoFinanceiro {

    private int id;
    private int clubeId;
    private String descricao;
    private String tipo;
    private String categoria;
    private double valor;
    private String dataCompetencia;

    public LancamentoFinanceiro(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("id não pode ser < 0");
        }
        this.id = id;
    }

    public int getClubeId() {
        return clubeId;
    }

    public void setClubeId(int clubeId) {
        this.clubeId = clubeId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataCompetencia() {
        return dataCompetencia;
    }

    public void setDataCompetencia(String dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }
}