package com.example.vacancia.utils;

import com.example.vacancia.interfaces.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Conexion
{
    private static final String URL_001 = "http://192.168.0.27:8080";

    public static API getUsuarioInterface()
    {
        //return Cliente.getCliente(URL_001).create(EmpleadoInterface.class);
        return Cliente.getCliente(URL_001).create(API.class);
    }
}
