package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Sorteio extends AppCompatActivity {

    private Button objBotao;
    private TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio);

        Random random = new Random();
        objBotao = findViewById(R.id.botaoSort);

        objBotao.setOnClickListener(v ->{

            int numero = random.nextInt();

            num = findViewById(R.id.num);
            num.setText(String.valueOf(numero));
            num.setVisibility(View.VISIBLE);
        });
    }

}