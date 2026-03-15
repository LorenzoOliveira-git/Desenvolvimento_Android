package com.example.appgemini;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.ai.FirebaseAI;
import com.google.firebase.ai.GenerativeModel;
import com.google.firebase.ai.java.GenerativeModelFutures;
import com.google.firebase.ai.type.Content;
import com.google.firebase.ai.type.GenerateContentResponse;
import com.google.firebase.ai.type.GenerativeBackend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ImageButton bt;
    TextView saida;
    TextInputEditText pergunta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.botaoEnviar);
        saida = findViewById(R.id.texto);
        pergunta = findViewById(R.id.inserirTexto);

        saida.setMovementMethod(new ScrollingMovementMethod());
        List<Message> conversationHistory = new ArrayList<>();

        // Initialize the Gemini Developer API backend service
// Create a `GenerativeModel` instance with a model that supports your use case

        GenerativeModel ai = FirebaseAI.getInstance(GenerativeBackend.googleAI())
                .generativeModel("gemini-2.5-flash-lite");

// Use the GenerativeModelFutures Java compatibility layer which offers
// support for ListenableFuture and Publisher APIs
        GenerativeModelFutures model = GenerativeModelFutures.from(ai);

        bt.setOnClickListener(l ->{
//            saida.setText(saida.getText().toString());

            // Criar o prompt
            Content prompt = new Content.Builder()
                    .addText(pergunta.getText().toString())
                    .build();

//            Chamar o gemini de modo assicrono -> criar tarefa
            Executor executor = Executors.newSingleThreadExecutor();

            ListenableFuture<GenerateContentResponse> response = model.generateContent(prompt);
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    String resultText = result.getText();
                    conversationHistory.add(
                            new Message("user", pergunta.getText().toString(), System.currentTimeMillis())
                    );
                    conversationHistory.add(
                            new Message("assistant",resultText,System.currentTimeMillis())
                    );
                    String finalText = "";
                    for(Message message: conversationHistory){
                        if(message.getRole() == "user"){
                            finalText+="User: "+message.getContent().toString()+"\n\n";
                        }
                        if(message.getRole()=="assistant"){
                            finalText+="Assistant: "+message.getContent().toString()+"\n\n";
                        }
                    }
                    saida.setText(finalText);
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            }, executor);
        });
    }
}