package com.origin.auxilio_emergencial.service;

public class CodeError {
    public static final Integer REQUEST_ERROR = 1;

    public static final String getMenssage(Integer code){
        switch (code){
            case 1 : return "Falha na requisição.";
            case 2 : return "Erro desconhecido";
            default: return "Erro!";
        }
    }
}
