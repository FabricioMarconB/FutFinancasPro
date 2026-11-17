package model;

public class TipoUsuario {
    
    private int id;
    private String moduloAdministrativo;
    private String moduloElenco;
    private String moduloFinanceiro;

    public TipoUsuario(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if( id < 0 ) {
            throw new IllegalArgumentException("id não pode ser < 0");
        }
        this.id = id;
    }

    public String getModuloAdministrativo() {
        return moduloAdministrativo;
    }

    public void setModuloAdministrativo(String moduloAdministrativo) {
        if( moduloAdministrativo == null ) {
            throw new IllegalArgumentException("moduloAdministrativo não pode ser null");
        }
        this.moduloAdministrativo = moduloAdministrativo;
    }

    public String getModuloElenco() {
        return moduloElenco;
    }

    public void setModuloElenco(String moduloElenco) {
        if( moduloElenco == null ) {
            throw new IllegalArgumentException("moduloElenco não pode ser null");
        }
        this.moduloElenco = moduloElenco;
    }

    public String getModuloFinanceiro() {
        return moduloFinanceiro;
    }

    public void setModuloFinanceiro(String moduloFinanceiro) {
        if( moduloFinanceiro == null ) {
            throw new IllegalArgumentException("moduloFinanceiro não pode ser null");
        }
        this.moduloFinanceiro = moduloFinanceiro;
    }
    
    @Override
    public String toString() {
        return "(" + getId() + ", " + getModuloAdministrativo() + ", " + getModuloElenco() + ", " + getModuloFinanceiro() + ")";
    }

}