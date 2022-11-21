package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

public class MiCuenta extends Activity
{
    API apiUsuario;
    TextView txtMiCuentaNombre;
    TextView txtMiCuentaApellido;
    TextView txtMiCuentaCorreo;
    TextView txtMiCuentaContrasena;

    Long ID_USUARIO;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_cuenta);


        Intent intent = getIntent();
        ID_USUARIO =  Long.parseLong(intent.getStringExtra(ScreenMenu.EXTRA_MESSAGE));
        System.out.println("ID del usuario desde MI CUENTA "+ID_USUARIO );



        txtMiCuentaNombre = findViewById(R.id.txtMiCuentaNombre);
        txtMiCuentaApellido = findViewById(R.id.txtMiCuentaApellido);
        txtMiCuentaCorreo = findViewById(R.id.txtMiCuentaCorreo);
        txtMiCuentaContrasena = findViewById(R.id.txtMiCuentaContrasena);

        login();

    }

    private void login()
    {
        apiUsuario = Conexion.getUsuarioInterface(); // hacer la conexion


        Call<List<Usuario>> call = apiUsuario.findAll();

        call.enqueue(new Callback<List<Usuario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuario = response.body();
                usuario.forEach(
                        p -> {
                            //Log.i("IMPORTANTE: "+p.getCorreo().toString(), response.toString());
                            if( p.getId() == ID_USUARIO)
                            {
                                txtMiCuentaNombre.setText(p.getNombre());
                                txtMiCuentaApellido.setText(p.getApellido());
                                txtMiCuentaCorreo.setText(p.getCorreo());
                                txtMiCuentaContrasena.setText(p.getContrasena());

                                Toast.makeText(MiCuenta.this, "Usuario mostrado", Toast.LENGTH_SHORT).show();
                                //System.out.println("El usuario con correo: "+correoUsuario+" y contraseña: "+contrasenaUsuario+" sí existe en la base de datos");
                            }
                            else
                            {
                                Toast.makeText(MiCuenta.this, "Usuario no existe en el sistema", Toast.LENGTH_SHORT).show();
                                //System.out.println("El usuario con correo: "+correoUsuario+" y contraseña: "+contrasenaUsuario+" no existe en la base de datos");
                            }
                            //System.out.println(p.getCorreo().toString());
                            System.out.println("IMPORTANTE");
                        }
                );

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(MiCuenta.this, "Error", Toast.LENGTH_SHORT).show();
                System.out.println("AVISO");
                Log.e("Error", t.getMessage());
            }
        });


    }
}
