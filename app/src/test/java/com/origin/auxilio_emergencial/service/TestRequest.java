package com.origin.auxilio_emergencial.service;

import static org.junit.Assert.assertEquals;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.origin.auxilio_emergencial.models.Cpf;
import com.origin.auxilio_emergencial.models.DateDeserializer;
import com.origin.auxilio_emergencial.models.DateSerializer;
import com.origin.auxilio_emergencial.models.Parcela;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestRequest {

    @Test
    public void request() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter( Date.class, new DateSerializer())
                .registerTypeAdapter( Date.class, new DateDeserializer() )
                .create();

        Retrofit retrofit =  new Retrofit
                .Builder()
                .addConverterFactory( GsonConverterFactory.create(gson))
                .baseUrl("http://127.0.0.1:8000/")
                .build();

        Request request  = retrofit.create(Request.class);

        Cpf cpf = new Cpf();

        cpf.setCpf( "97775746220" );

        Call<List<Parcela>> callAsync = request.getA( cpf );

        Response<List<Parcela>> s = callAsync.execute();

        for (Parcela p : s.body()){
            System.out.println( p.getId() );
        }

    }
}