package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Login extends Activity
{
    Button btnLogin;
    EditText editLogCorreo;
    EditText editLogContrasena;
    API apiUsuario;
    public static String EXTRA_MESSAGE = "com.example.vacancia.MESSAGE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent intent = getIntent();
        intent.getAction();

        editLogCorreo = findViewById(R.id.editLogCorreo);
        editLogContrasena = findViewById(R.id.editLogContrasena);
        btnLogin = findViewById(R.id.bntLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }


    private void login()
    {
        apiUsuario = Conexion.getUsuarioInterface(); // hacer la conexion

        String correoUsuario = editLogCorreo.getText().toString();
        String contrasenaUsuario = editLogContrasena.getText().toString();

        Call<List<Usuario>> call = apiUsuario.findAll();

        call.enqueue(new Callback<List<Usuario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuario = response.body();
                usuario.forEach(
                        p -> {
                            //Log.i("IMPORTANTE: "+p.getCorreo().toString(), response.toString());
                            if( p.getCorreo().toString().equals(correoUsuario) && p.getContrasena().toString().equals(contrasenaUsuario))
                            {
                                String ID_USUARIO = p.getId().toString();
                                System.out.println("id del usuario: "+ID_USUARIO);
                                Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, ScreenMenu.class);
                                intent.putExtra(EXTRA_MESSAGE ,ID_USUARIO);
                                startActivity(intent);
                                //System.out.println("El usuario con correo: "+correoUsuario+" y contraseña: "+contrasenaUsuario+" sí existe en la base de datos");
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Usuario no existe en el sistema", Toast.LENGTH_SHORT).show();
                                //System.out.println("El usuario con correo: "+correoUsuario+" y contraseña: "+contrasenaUsuario+" no existe en la base de datos");
                            }
                            //System.out.println(p.getCorreo().toString());
                            System.out.println("IMPORTANTE");
                        }
                );

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                System.out.println("AVISO");
                Log.e("Error", t.getMessage());
            }
        });


    }
}
