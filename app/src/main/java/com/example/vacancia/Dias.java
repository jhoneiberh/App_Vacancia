package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class Dias extends Activity
{

    ImageButton imgBtnDiasBefore;
    ImageButton imgBtnDiasNext;
    EditText editDias;
    int PRESUPUESTO;

    /**
     * Se debe enviar a la siguiente activiadad tanto la cantidad de presupuesto en dinero
     * Así como la cantidad de días
     */

    public static String EXTRA_MESSAGE_PRESUPUESTO = "com.example.vacancia.MESSAGE";
    public static String EXTRA_MESSAGE_DIAS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dias);


        imgBtnDiasBefore = findViewById(R.id.imgBtnDiasBefore);
        imgBtnDiasNext = findViewById(R.id.imgBtnDiasNext);
        editDias = findViewById(R.id.editDias);


        Intent intent = getIntent();
        PRESUPUESTO =  Integer.parseInt(intent.getStringExtra(Presupuesto.EXTRA_MESSAGE));
        System.out.println("DINERO DE PRESUPUESTO "+PRESUPUESTO );

        Intent intent0 = getIntent();
        intent0.getAction();



        imgBtnDiasNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cantidad_DIAS = editDias.getText().toString();
                String presupuesto_DIAS = String.valueOf(PRESUPUESTO);
                System.out.println("cantidad de DIAS: "+cantidad_DIAS);
                Intent intent1 = new Intent(Dias.this, Lugares.class);
                intent1.putExtra(EXTRA_MESSAGE_DIAS, cantidad_DIAS);
                intent1.putExtra(EXTRA_MESSAGE_PRESUPUESTO, presupuesto_DIAS);
                startActivity(intent1);
            }
        });
    }
}
