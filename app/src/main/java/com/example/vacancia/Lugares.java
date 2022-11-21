package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.vacancia.interfaces.API;
import com.example.vacancia.model.Lugar;
import com.example.vacancia.utils.Conexion;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lugares extends Activity {
    Long presupuesto;
    Long dias;
    API apiUsuario;
    TextView showLugaresNombre;
    ImageView imageLugares;
    ListView listView;
    TextView txtLugaresPrecio;
    TextView txtLugaresID;
    Button btnLugaresBuscar;
    EditText editLugaresNombre;

    public static String EXTRA_MESSAGE = "com.example.vacancia.MESSAGE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lugares);

        showLugaresNombre = findViewById(R.id.showLugaresNombre);
        txtLugaresPrecio = findViewById(R.id.txtLugaresPrecio);
        txtLugaresID = findViewById(R.id.txtLugaresNameInput);
        btnLugaresBuscar = findViewById(R.id.btnLugaresBuscar);
        editLugaresNombre = findViewById(R.id.editLugaresNombre);

        Intent intent = getIntent();
        presupuesto =  Long.parseLong(intent.getStringExtra(Dias.EXTRA_MESSAGE_PRESUPUESTO));
        dias = Long.parseLong(intent.getStringExtra(Dias.EXTRA_MESSAGE_DIAS));
        System.out.println("CANTIDAD DE PRESUPUESTO DESDE LUGARES "+presupuesto );
        System.out.println("CANTIDAD DE DIAS DESDE LUGARES "+dias);

        buscarCiudades();


        btnLugaresBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("NOMBRE DEL LUGAR DESDE LUGARES EN MINUSCULA: "+editLugaresNombre.getText().toString());
                String nombreLugar = editLugaresNombre.getText().toString().toUpperCase();
                System.out.println("NOMBRE DEL LUGAR LUGAR DESDE LUGARES EN MAYUSCULA: "+nombreLugar.toUpperCase());
                Intent intent1 = new Intent(Lugares.this, MostrarLugar.class);
                intent1.putExtra(EXTRA_MESSAGE, nombreLugar);
                startActivity(intent1);
            }
        });
    }

    private void buscarCiudades()
    {
        System.out.println("ENTRADO EN LA FUNCION BUSCAR CIUDADADES");
        apiUsuario = Conexion.getUsuarioInterface(); // hacer la conexion
        System.out.println("ANTES DE LA LLAMADA DEL MÉTODO DEL BACKEND");
        Call<List<Lugar>> call = apiUsuario.findAllLugares();

        call.enqueue(new Callback<List<Lugar>>() {
           @RequiresApi(api = Build.VERSION_CODES.N)
           @Override
           public void onResponse(Call<List<Lugar>> call, Response<List<Lugar>> response) {
               System.out.println("DENTRO DEL RESPONSE");
               List<Lugar> lugares = response.body();

               lugares.forEach( p -> {
                   if ( p.getPrecio() <= presupuesto && p.getDias() <= dias )
                   {
                       DecimalFormat format = new DecimalFormat("###,###.##");
                       showLugaresNombre.append(p.getNombre()+"\n");
                       //txtLugaresPrecio.append(" $ "+p.getPrecio().toString()+"\n");
                       //txtLugaresID.append(p.getId().toString()+"\n");
                       txtLugaresPrecio.append(" $ "+format.format(p.getPrecio())+"\n");
                   }
               });

           }

           @Override
           public void onFailure(Call<List<Lugar>> call, Throwable t) {
               System.out.println("DENTRO DEL ONFAILURE");
               Log.e("Error ", t.getMessage());
           }
       });
    }
}

