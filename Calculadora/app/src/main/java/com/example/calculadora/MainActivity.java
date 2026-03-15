package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.calculadora.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TextView resultado;
    MaterialButton zero;
    MaterialButton um;
    MaterialButton dois;
    MaterialButton tres;
    MaterialButton quatro;
    MaterialButton cinco;
    MaterialButton seis;
    MaterialButton sete;
    MaterialButton oito;
    MaterialButton nove;
    MaterialButton menos;
    MaterialButton mais;
    MaterialButton multi;
    MaterialButton div;
    MaterialButton apagar;
    MaterialButton igual;

    List<String> equacao = new ArrayList<>();
    Set<String> operadores = new HashSet<>(Arrays.asList("+","-","*","/"));
    int quantOperacoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resultado = binding.resultado;

        zero = binding.zero;
        um = binding.um;
        dois = binding.dois;
        tres = binding.tres;
        quatro = binding.quatro;
        cinco = binding.cinco;
        seis = binding.seis;
        sete = binding.sete;
        oito = binding.oito;
        nove = binding.nove;

        mais = binding.mais;
        menos = binding.menos;
        multi = binding.multi;
        div = binding.div;
        apagar = binding.apagar;
        igual = binding.igual;

        zero.setOnClickListener(l ->{
            resultado.append("0");
            equacao.add("0");
        });

        um.setOnClickListener(l ->{
            resultado.append("1");
            equacao.add("1");
        });

        dois.setOnClickListener(l ->{
            resultado.append("2");
            equacao.add("2");
        });

        tres.setOnClickListener(l ->{
            resultado.append("3");
            equacao.add("3");
        });

        quatro.setOnClickListener(l ->{
            resultado.append("4");
            equacao.add("4");
        });

        cinco.setOnClickListener(l ->{
            resultado.append("5");
            equacao.add("5");
        });

        seis.setOnClickListener(l ->{
            resultado.append("6");
            equacao.add("6");
        });

        sete.setOnClickListener(l ->{
            resultado.append("7");
            equacao.add("7");
        });

        oito.setOnClickListener(l ->{
            resultado.append("8");
            equacao.add("8");
        });

        nove.setOnClickListener(l ->{
            resultado.append("9");
            equacao.add("9");
        });

        menos.setOnClickListener(l ->{
            if(!operadores.contains(equacao.get(-1))) {
                resultado.append("-");
                equacao.add("-");
                quantOperacoes++;
            }
        });

        mais.setOnClickListener(l ->{
            if(!operadores.contains(equacao.get(equacao.size()-1))) {
                resultado.append("+");
                equacao.add("+");
                quantOperacoes++;
            }
        });

        multi.setOnClickListener(l ->{
            if(!operadores.contains(equacao.get(-1))) {
                resultado.append("*");
                equacao.add("*");
                quantOperacoes++;
            }
        });

        div.setOnClickListener(l ->{
            if(!operadores.contains(equacao.get(-1))) {
                resultado.append("/");
                equacao.add("/");
                quantOperacoes++;
            }
        });

        apagar.setOnClickListener(l ->{
            equacao.clear();
            resultado.setText("");
        });

        igual.setOnClickListener(l ->{
            int valorFinal = 0;
            for (int i = 0; i<equacao.size();i++){
                String caractere = equacao.get(i);
                if(equacao.isEmpty()){
                    break;
                }else if(!operadores.contains(caractere)){}
                else{
                    if(caractere.equals("*")){
                        int index = equacao.indexOf("*");
                        valorFinal += Integer.parseInt(equacao.get(index-1)) * Integer.parseInt(equacao.get(index+1)) ;
                        equacao.remove(index-1);
                        equacao.remove(index);
                        equacao.remove(index+1);
                        i -= 3;
                        equacao.add(index-1,String.valueOf(valorFinal));
                    }
                    if(caractere.equals("/")){
                        int index = equacao.indexOf("/");
                        valorFinal += Integer.parseInt(equacao.get(index-1)) / Integer.parseInt(equacao.get(index+1)) ;
                        equacao.remove(index-1);
                        equacao.remove(index);
                        equacao.remove(index+1);
                        i -= 3;
                        equacao.add(String.valueOf(valorFinal));
                    }
                }
            }
            for (int i = 0; i<equacao.size(); i++){
                String caractere = equacao.get(i);
                if(equacao.isEmpty()){
                    break;
                }else if(!operadores.contains(caractere)){}
                else{
                    if(caractere.equals("+")){
                        valorFinal += Integer.parseInt(equacao.get(i-1)) + Integer.parseInt(equacao.get(i+1)) ;
                        equacao.remove(i-1);
                        equacao.remove(i);
                        equacao.remove(i+1);
                        i ++;
                        equacao.add(String.valueOf(valorFinal));
                    }
                    if(caractere.equals("-")){
                        int index = equacao.indexOf("-");
                        valorFinal += Integer.parseInt(equacao.get(index-1)) - Integer.parseInt(equacao.get(index+1)) ;
                        equacao.remove(index-1);
                        equacao.remove(index);
                        equacao.remove(index+1);
                        i -= 3;
                        equacao.add(String.valueOf(valorFinal));
                    }
                }
            }
            resultado.setText(String.valueOf(valorFinal));
            equacao.clear();
        });
    }

}