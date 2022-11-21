package com.example.vacancia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Acerca_de extends Activity
{

    private ImageButton imgBtnFacebook;
    private ImageButton imgBtnTwitter;
    private ImageButton imgBtnYoutube;
    private String URL_TO_YOUTUBE = "http://www.youtube.com";
    private String URL_TO_FACEBOOK = "http://www.facebook.com";
    private String URL_TO_TWITTER = "http://www.twitter.com";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_de);


        imgBtnFacebook = (ImageButton) findViewById(R.id.imgBtnFacebook);
        imgBtnTwitter = (ImageButton) findViewById(R.id.imgBtnTwitter);
        imgBtnYoutube = (ImageButton) findViewById(R.id.imgBtnYoutube);



        /**
         * Botones de redes sociales
         */
        imgBtnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.youtube.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://twitter.com/BlackClover_EN");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com/blackclover.fans/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Agsignar mensaje a los botones
     */


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        Intent intent = new Intent(Acerca_de.this, MainActivity.class);
        switch (id)
        {
            case R.id.itemMiCuenta:
                Toast.makeText(this, "Bienvenido a Mi Cuenta", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.itemAcercaDe:
                Toast.makeText(this, "Bienvenido a Acer ca", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
