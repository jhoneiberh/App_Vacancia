package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.vacancia.interfaces.API;
import com.example.vacancia.model.Usuario;
import com.example.vacancia.utils.Conexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends Activity
{

    /**
     * Agregar funcionalidad de que no hayan emails repetidos
     * Verificar si existe ya en la base de datos.
     */
    API apiUsuario;
    Button btnRegistrar;
    EditText editRegNombre;
    EditText editRegApellido;
    EditText editRegCorreo;
    EditText editRegContrasena;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        Intent intent = getIntent();
        intent.getAction();

        editRegNombre = findViewById(R.id.editRegNombre);;
        editRegApellido = findViewById(R.id.editRegApellido);;
        editRegCorreo = findViewById(R.id.editRegCorreo);;
        editRegContrasena = findViewById(R.id.editRegContrasena);;


        btnRegistrar = findViewById(R.id.btnRegistro);



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setNombre(editRegNombre.getText().toString());
                usuario.setApellido(editRegApellido.getText().toString());
                usuario.setCorreo(editRegCorreo.getText().toString());
                usuario.setContrasena(editRegContrasena.getText().toString());
                addUsuario(usuario);
            }
        });
    }



    public void addUsuario(Usuario usuario)
    {
        apiUsuario = Conexion.getUsuarioInterface(); // hacer la conexion

        Call<Usuario> call = apiUsuario.addUsuario(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if( response != null )
                {
                    Usuario usuario = response.body();
                    Toast.makeText(Registro.this, "Se agregó con éxito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });

    }
}
