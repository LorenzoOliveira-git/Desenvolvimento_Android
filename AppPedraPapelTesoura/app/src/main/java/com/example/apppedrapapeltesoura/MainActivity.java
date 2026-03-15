package com.example.apppedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button botaoJogar;
    ImageView pedra;
    ImageView papel;
    ImageView tesoura;
    TextView resultado;
    ImageView imagemRoleta;
    ImageView imagemSelecionada;
    int[] imagens = {
            R.drawable.pedra,
            R.drawable.papel,
            R.drawable.tesoura,
    };
    Random random = new Random();
    Handler handler;
    boolean rodando;
    int imagemSorteadaFinal;
    String valorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorSelecionado = "";
        botaoJogar = findViewById(R.id.botaoJogar);

        pedra = findViewById(R.id.pedra);
        papel = findViewById(R.id.papel);
        tesoura = findViewById(R.id.tesoura);

        View.OnClickListener listener = i -> {

            resetarImagens();

            i.setScaleX(1.2f);
            i.setScaleY(1.2f);

            imagemSelecionada = (ImageView) i ;

            deixarOutrasCinza((ImageView) i);
        };

        pedra.setOnClickListener(listener);
        papel.setOnClickListener(listener);
        tesoura.setOnClickListener(listener);

        imagemRoleta = findViewById(R.id.escolhaApp);
        resultado = findViewById(R.id.resultado);
        handler = new Handler(Looper.getMainLooper());
        botaoJogar.setOnClickListener(v -> {
            rodando = false;
            if(imagemSelecionada!= null){
                valorSelecionado = imagemSelecionada.getTag().toString();
                this.girarRoleta();
            }

        });

    }
    private void resetarImagens() {
        pedra.setScaleX(1f);
        pedra.setScaleY(1f);
        pedra.clearColorFilter();

        papel.setScaleX(1f);
        papel.setScaleY(1f);
        papel.clearColorFilter();

        tesoura.setScaleX(1f);
        tesoura.setScaleY(1f);
        tesoura.clearColorFilter();
    }
    private void deixarOutrasCinza(ImageView selecionada) {

        ImageView[] imagens = {pedra, papel, tesoura};

        for (ImageView img : imagens) {
            if (img != selecionada) {
                img.setColorFilter(
                        android.graphics.Color.GRAY,
                        android.graphics.PorterDuff.Mode.MULTIPLY
                );
            }
        }
    }

    private void girarRoleta() {

        if (rodando) {
            return;
        }

        rodando = true;

        final long tempoInicio = System.currentTimeMillis();
        final long duracao = 5000;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                long tempoAtual = System.currentTimeMillis();

                if (tempoAtual - tempoInicio < duracao) {

                    int indice = random.nextInt(imagens.length);
                    imagemRoleta.setImageResource(imagens[indice]);

                    imagemSorteadaFinal = imagens[indice];

                    handler.postDelayed(this, 100);

                } else {
                    rodando = false;
                    verificarResultado();
                }
            }
        };
        handler.post(runnable);
    }

    private void verificarResultado() {

        if(imagemSorteadaFinal == R.drawable.papel){
            if(valorSelecionado.equals("pedra")){
                resultado.setText("Derrota!");
            }else if(valorSelecionado.equals("papel")){
                resultado.setText("Empate!");
            }else{
                resultado.setText("Vitória!");
            }
        }else if(imagemSorteadaFinal == R.drawable.pedra){
            if(valorSelecionado.equals("pedra")){
                resultado.setText("Empate!");
            }else if(valorSelecionado.equals("papel")){
                resultado.setText("Vitória!");
            }else{
                resultado.setText("Derrota!");
            }
        }else{
            if(valorSelecionado.equals("pedra")){
                resultado.setText("Vitória!");
            }else if(valorSelecionado.equals("papel")){
                resultado.setText("Derrota!");
            }else{
                resultado.setText("Empate!");
            }
        }

        resultado.setVisibility(View.VISIBLE);
    }
}