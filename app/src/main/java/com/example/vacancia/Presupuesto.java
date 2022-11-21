package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Presupuesto extends Activity
{

    ImageButton imgBtnBefore;
    ImageButton imgBtnPresupuestoNext;
    EditText editPresupuestoDinero;


    public static String EXTRA_MESSAGE = "com.example.vacancia.MESSAGE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presupuesto);


        imgBtnBefore = findViewById(R.id.imgBtnBefore);
        imgBtnPresupuestoNext = findViewById(R.id.imgBtnPresupuestoNext);
        editPresupuestoDinero = findViewById(R.id.editPresupuestoDinero);


        Intent intent = getIntent();
        intent.getAction();



        imgBtnPresupuestoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Valor_PRESUPUESTO = editPresupuestoDinero.getText().toString();
                System.out.println("VALOR DEL PRESUPUESTO: "+Valor_PRESUPUESTO);
                Intent intent1 = new Intent(Presupuesto.this, Dias.class);
                intent1.putExtra(EXTRA_MESSAGE ,Valor_PRESUPUESTO);
                startActivity(intent1);
            }
        });
    }
}
