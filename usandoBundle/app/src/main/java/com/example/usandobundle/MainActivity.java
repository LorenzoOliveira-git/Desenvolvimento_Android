package com.example.usandobundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText telefone;
    EditText email;
    Button btinformacao;
    Button btdiscar;
    Button btemail;
    Button btappinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        telefone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);

        btinformacao = findViewById(R.id.btinformacao);
        btdiscar = findViewById(R.id.btdiscar);
        btemail = findViewById(R.id.btemail);
        btappinfo = findViewById(R.id.btappinfo);

        btinformacao.setOnClickListener(v ->{
//            Criar a rota
            Intent rota = new Intent(this, Informacao.class);

//            Cirar o envelope de dados
            Bundle envelopeDados = new Bundle();
            envelopeDados.putString("name",nome.getText().toString());
            envelopeDados.putInt("phone", Integer.parseInt(telefone.getText().toString()));
            envelopeDados.putString("email",email.getText().toString());

            rota.putExtras(envelopeDados);
            startActivity(rota);

        });


    }
}