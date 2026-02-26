package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class Conversao extends AppCompatActivity {

    EditText valor;
    String stringValue;

    Double valorDolar;
    Double valorDouble;
    TextView text;
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversao);


        botao = findViewById(R.id.converterBotao);

        botao.setOnClickListener(b ->{
            valor = (EditText)findViewById(R.id.valor);
            stringValue = valor.getText().toString();
            valorDouble = Double.parseDouble(stringValue);

            valorDolar = valorDouble / 5.154;

            text = findViewById(R.id.valorDolar);
            text.setText(String.valueOf(valorDolar));
            text.setVisibility(View.VISIBLE);
        });
    }



}
