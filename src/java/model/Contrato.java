package model;

public class Contrato {

    private int id;
    private int clubeId;
    private int atletaId;
    private double salario;
    private double multaRescisoria;
    private String dataInicio;
    private String dataFim;
    private String status;

    public Contrato(int id) {
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

    public int getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(int atletaId) {
        this.atletaId = atletaId;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getMultaRescisoria() {
        return multaRescisoria;
    }

    public void setMultaRescisoria(double multaRescisoria) {
        this.multaRescisoria = multaRescisoria;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}