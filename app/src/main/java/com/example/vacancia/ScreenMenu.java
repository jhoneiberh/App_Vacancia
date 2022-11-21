package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ScreenMenu extends Activity
{
    /*Button btnmenuBuscarCiudad;
    Button btnMenuAcercaDe;
    Button btnMenuMiCuenta;*/

    ImageButton btnmenuBuscarCiudad;
    ImageButton btnMenuAcercaDe;
    ImageButton btnMenuMiCuenta;
    ImageButton imgBtnMenuBefore;

    public static String EXTRA_MESSAGE = "com.example.vacancia.MESSAGE";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_menu);

        /**
         * ID del usuario desde el login
         */
        Long ID_USUARIO;
        Intent intent = getIntent();
        ID_USUARIO =  Long.parseLong(intent.getStringExtra(Login.EXTRA_MESSAGE));
        System.out.println("ID del usuario desde el SCREEN MENU "+ID_USUARIO );






        btnMenuAcercaDe = findViewById(R.id.btnMenuAcercaDe);
        btnmenuBuscarCiudad = findViewById(R.id.btnmenuBuscarCiudad);
        btnMenuMiCuenta = findViewById(R.id.btnMenuMiCuenta);
        imgBtnMenuBefore = findViewById(R.id.imgBtnMenuBefore);


        btnMenuAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenMenu.this, Acerca_de.class);
                startActivity(intent);
            }
        });

        btnMenuMiCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenMenu.this, MiCuenta.class);
                String ID_ScreenMenu =  ID_USUARIO.toString();
                intent.putExtra(EXTRA_MESSAGE, ID_ScreenMenu);
                startActivity(intent);
            }
        });


        btnmenuBuscarCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenMenu.this, Presupuesto.class);
                startActivity(intent);
            }
        });

        imgBtnMenuBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenMenu.this, Login.class);
            }
        });


    }

}
