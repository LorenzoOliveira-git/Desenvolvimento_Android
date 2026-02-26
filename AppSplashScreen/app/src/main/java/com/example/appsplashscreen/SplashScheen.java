package com.example.appsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScheen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scheen);

//        Criar uma nova tarefa (um processo em segundo plano)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
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