package com.origin.auxilio_emergencial.service;

import androidx.lifecycle.MutableLiveData;

import com.origin.auxilio_emergencial.models.Cpf;
import com.origin.auxilio_emergencial.models.Parcela;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    Request request;
    Retrofit retrofit;
    MutableLiveData<String> sucesso;
    MutableLiveData<Integer> erro;
    MutableLiveData<List<Parcela>> response;

    public Service(
            MutableLiveData<String> sucesso,
            MutableLiveData<Integer> erro,
            MutableLiveData<List<Parcela>> response
    ){
        retrofit =  new Retrofit
                .Builder()
                .addConverterFactory( GsonConverterFactory.create())
                .baseUrl("https://auxilio.mgjobs.cf/")
                .build();

        request = retrofit.create(Request.class);

        this.sucesso = sucesso;
        this.erro = erro;
        this.response = response;

    }

    private Request getRequest(){
        return request;
    }

    public void makeCall(Cpf cpf ){
        Call<List<Parcela>> callAsync = request.getA(cpf);

        callAsync.enqueue( new Callback<List<Parcela>>() {
            @Override
            public void onResponse(Call<List<Parcela>> call, Response<List<Parcela>> response) {
                if(response.isSuccessful()){
                    Service.this.sucesso.postValue( "Sucesso" );
                    Service.this.response.postValue( response.body() );
                }
            }

            @Override
            public void onFailure(Call<List<Parcela>> call, Throwable t) {
                Service.this.erro.postValue(CodeError.REQUEST_ERROR);
            }
        } );

        return;
    }

}
