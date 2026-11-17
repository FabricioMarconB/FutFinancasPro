package model;

public class Atleta {

    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String posicao;
    private int numeroCamisa;

    public Atleta(int id) {
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
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            throw new IllegalArgumentException("CPF inválido! Formato esperado: 000.000.000-00");
        }
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        if (numeroCamisa <= 0) {
            throw new IllegalArgumentException("número da camisa deve ser positivo");
        }
        this.numeroCamisa = numeroCamisa;
    }

    @Override
    public String toString() {
        return "(" + getId() + ", " + getNome() + ", " + getPosicao() + ")";
    }
}