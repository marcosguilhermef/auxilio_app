package com.origin.auxilio_emergencial.service;


import com.origin.auxilio_emergencial.models.Cpf;
import com.origin.auxilio_emergencial.models.Parcela;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface Request {
    @POST("api/auxilio-cpf")
    Call<List<Parcela>> getA(@Body Cpf id);
}
