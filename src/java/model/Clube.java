package model;

public class Clube {

    private int id;
    private String nome;
    private String sigla;
    private String cnpj;
    private String estadio;
    private double orcamento;

    public Clube(int id) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        if (sigla == null || !sigla.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("sigla deve conter exatamente 3 letras maiúsculas (Ex: SEP, FLA, COR)");
        }
        this.sigla = sigla;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || !cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
            throw new IllegalArgumentException("cnpj inválido! Formato esperado: 00.000.000/0000-00");
        }
        this.cnpj = cnpj;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        if (orcamento < 0) {
            throw new IllegalArgumentException("orçamento não pode ser negativo");
        }
        this.orcamento = orcamento;
    }

    @Override
    public String toString() {
        return "(" + getId() + ", " + getNome() + ", " + getSigla() + ")";
    }
}