package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.media.ImageWriter;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.vacancia.interfaces.API;
import com.example.vacancia.model.Lugar;
import com.example.vacancia.utils.Conexion;
import com.google.android.material.internal.DescendantOffsetUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostrarLugar extends Activity
{
    API apiUsuario;

    ImageButton imgBtnLugarBefore;
    TextView showLugarNombre;
    TextView showLugarCiudad;
    ImageView imgLugar;
    ImageButton imgBtnLugarNext;
    TextView showLugarDesde;
    TextView showLugarPrecio;
    TextView showLugarDias;
    TextView showLugarOrigen;
    TextView showLugarDescGeneral;
    TextView showLugarDescripcion;
    String nombreLugar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lugar);


        // is uppercase
        Intent intent = getIntent();
        nombreLugar =  intent.getStringExtra(Lugares.EXTRA_MESSAGE);


        imgBtnLugarBefore = findViewById(R.id.imgBtnLugarBefore);
        showLugarNombre = findViewById(R.id.showLugarNombre);
        showLugarCiudad = findViewById(R.id.showLugarCiudad);
        imgLugar = findViewById(R.id.imgLugar);
        imgBtnLugarNext = findViewById(R.id.imgBtnLugarNext);
        showLugarDesde = findViewById(R.id.showLugarDesde);
        showLugarPrecio = findViewById(R.id.showLugarPrecio);
        showLugarDias = findViewById(R.id.showLugarDias);
        showLugarOrigen = findViewById(R.id.showLugarOrigen);
        showLugarDescGeneral = findViewById(R.id.showLugarDescGeneral);
        showLugarDescripcion = findViewById(R.id.showLugarDescripcion);


        mostrarLugar();
        System.out.println("NOMBRE DEL LUGAR DESDE MOSTRARLUGAR: "+nombreLugar);
    }


    private void mostrarLugar()
    {
        apiUsuario = Conexion.getUsuarioInterface(); // hacer la conexion

        Call<List<Lugar>> call = apiUsuario.findAllLugares();

        call.enqueue(new Callback<List<Lugar>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Lugar>> call, Response<List<Lugar>> response) {
                List<Lugar> lugares = response.body();

                lugares.forEach( p -> {
                    if( p.getNombre().toUpperCase().contains(nombreLugar) )
                    {

                        //System.out.println(p.getId());
                        System.out.println("NOMBRE DEL LUGAR DESDE MOSTRARLUGAR2: "+nombreLugar);
                        showLugarNombre.setText(p.getNombre());
                        showLugarPrecio.setText(p.getPrecio().toString()+" pesos");
                        showLugarOrigen.setText("Salida desde "+p.getLugarOrigen());
                        showLugarDescripcion.setText(p.getDescripcion());
                        showLugarCiudad.setText(p.getCiudad());
                        showLugarDias.setText(p.getDias().toString() +" dias");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Lugar>> call, Throwable t) {
                System.out.println("ENTRÓ EN EL FAILURE " + t.getMessage());
            }
        });



    }
}
