package com.example.vacancia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button bntEntrar;
    Button btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Vacancia);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bntEntrar = findViewById(R.id.btnEntrarCuenta);
        btnCrearCuenta = findViewById(R.id.btnCrearCruenta);


        /**
         * Botón que lee el boton de querer ir a la venta de Iniciar Sesión
         */
        bntEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}