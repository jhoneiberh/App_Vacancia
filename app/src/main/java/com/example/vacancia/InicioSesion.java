package com.example.vacancia;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.vacancia.interfaces.API;
import com.example.vacancia.model.Usuario;
import com.example.vacancia.utils.Conexion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioSesion extends Activity
{
    private EditText correo;
    private EditText contrasena;
    API apiUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        correo = findViewById(R.id.editLogCorreo);
        contrasena = findViewById(R.id.editLogContrasena);

        findViewById(R.id.bntLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login()
    {
        apiUsuario = Conexion.getUsuarioInterface(); // hacer la conexion

        String correoUsuario = correo.getText().toString();
        String contrasenaUsuario = contrasena.getText().toString();

        Call<List<Usuario>> call = apiUsuario.findAll();

        call.enqueue(new Callback<List<Usuario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuario = response.body();
                usuario.forEach(
                        p -> {
                        correo.setText(p.getCorreo().toString());
                        //Log.i("IMPORTANTE: "+p.getCorreo().toString(), response.toString());
                            if( p.getCorreo().toString().equals(correoUsuario) && p.getContrasena().toString().equals(contrasenaUsuario))
                            {
                                System.out.println("El usuario con correo: "+correoUsuario+" y contraseña: "+contrasenaUsuario+" sí existe en la base de datos");
                            }
                            else
                            {
                                System.out.println("El usuario con correo: "+correoUsuario+" y contraseña: "+contrasenaUsuario+" no existe en la base de datos");
                            }
                        //System.out.println(p.getCorreo().toString());
                        System.out.println("IMPORTANTE");
                        }
                );

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(InicioSesion.this, "Error", Toast.LENGTH_SHORT).show();
                System.out.println("AVISO");
                Log.e("Error", t.getMessage());
            }
        });


    }
}
