package com.origin.auxilio_emergencial.models;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable;

public class Parcela implements Parcelable {
    private int codigo;
    private String nome;

    Double id;
    String mesDisponibilizacao;
    Beneficiario beneficiario;
    ResponsavelAuxilioEmergencial responsavelAuxilioEmergencial;
    Municipio municipio;
    String situacaoAuxilioEmergencial;
    String enquadramentoAuxilioEmergencial;
    Float valor;
    String numeroParcela;

    private Parcela(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;

    }

    private Parcela(Parcel in) {
        codigo = in.readInt();
        nome = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(codigo);
        dest.writeString(nome);

    }

    public static final Parcelable.Creator<Parcela>
            CREATOR = new Parcelable.Creator<Parcela>() {

        public Parcela createFromParcel(Parcel in) {
            return new Parcela(in);
        }

        public Parcela[] newArray(int size) {
            return new Parcela[size];
        }
    };


    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getMesDisponibilizacao() {
        return mesDisponibilizacao;
    }

    public void setMesDisponibilizacao(String mesDisponibilizacao) {
        this.mesDisponibilizacao = mesDisponibilizacao;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public ResponsavelAuxilioEmergencial getResponsavelAuxilioEmergencial() {
        return responsavelAuxilioEmergencial;
    }

    public void setResponsavelAuxilioEmergencial(ResponsavelAuxilioEmergencial responsavelAuxilioEmergencial) {
        this.responsavelAuxilioEmergencial = responsavelAuxilioEmergencial;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getSituacaoAuxilioEmergencial() {
        return situacaoAuxilioEmergencial;
    }

    public void setSituacaoAuxilioEmergencial(String situacaoAuxilioEmergencial) {
        this.situacaoAuxilioEmergencial = situacaoAuxilioEmergencial;
    }

    public String getEnquadramentoAuxilioEmergencial() {
        return enquadramentoAuxilioEmergencial;
    }

    public void setEnquadramentoAuxilioEmergencial(String enquadramentoAuxilioEmergencial) {
        this.enquadramentoAuxilioEmergencial = enquadramentoAuxilioEmergencial;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

}
