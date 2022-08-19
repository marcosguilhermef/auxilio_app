package com.origin.auxilio_emergencial.ui.consultar_auxilio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.origin.auxilio_emergencial.models.Parcela;
import java.util.List;

public class ConsultarAuxilioViewModel extends ViewModel {
    public MutableLiveData<String> sucesso = new MutableLiveData<>();
    public MutableLiveData<Integer> erro  = new MutableLiveData<>();
    public MutableLiveData<List<Parcela>> parcelas  = new MutableLiveData<>();

    public MutableLiveData<String> getSucesso(){
        return sucesso;
    }

    public MutableLiveData<Integer> getErro(){
        return erro;
    }

    public MutableLiveData<List<Parcela>> getParcelas(){
        return parcelas;
    }

    public void renew(){
        parcelas = new MutableLiveData<>();
        erro  = new MutableLiveData<>();
    }
}
