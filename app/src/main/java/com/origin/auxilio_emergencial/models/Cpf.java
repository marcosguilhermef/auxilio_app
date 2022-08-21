package com.origin.auxilio_emergencial.models;

public class Cpf {
    private String cpf;
    private String pagina;

    public Cpf(){

    }

    public Cpf(String cpf, String page){
        this.cpf = cpf;
        this.pagina = page;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
}
