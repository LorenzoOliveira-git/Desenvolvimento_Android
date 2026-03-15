package com.example.appsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScheen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scheen);

        ImageView imgG = findViewById(R.id.imgG);
        Glide.with(this)
                 .load(R.drawable.gif2)
                 .into(imgG);


//        Criar uma nova tarefa (um processo em segundo plano)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(12000);
                    //        Carregar a tela principal
//        Intent pode ser associado a uma rota (joga da rota inicial this, para a rota que você colocar)
                    Intent intent = new Intent(SplashScheen.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }).start();

    }
}