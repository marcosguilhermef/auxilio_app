package com.origin.auxilio_emergencial.models;

public class Beneficiario {
    private String cpfFormatado;
    private String nis;
    private String nome;

    public String getCpfFormatado() {
        return cpfFormatado;
    }

    public void setCpfFormatado(String cpfFormatado) {
        this.cpfFormatado = cpfFormatado;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
