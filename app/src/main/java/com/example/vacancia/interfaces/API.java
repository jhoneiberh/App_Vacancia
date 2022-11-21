package com.example.vacancia.interfaces;

import com.example.vacancia.MostrarLugar;
import com.example.vacancia.model.Lugar;
import com.example.vacancia.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API
{

    // se declaran todas las operaciones que se quieren consumir

    @POST("/guardaruser")
    public Call<Usuario> addUsuario(@Body Usuario usuario);


    @GET("/consultarAll")
    public Call<List<Usuario>> findAll();


    @GET("/consultarAllLugares")
    public Call<List<Lugar>> findAllLugares();


    @GET("/consultarporid")
    public Call<Usuario> findUserById(@Path("{id}") Long id);


}
