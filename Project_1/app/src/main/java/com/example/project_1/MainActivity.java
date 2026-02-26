package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ImageView objAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objAndroid = findViewById(R.id.imageView);

        objAndroid.setOnClickListener( v -> {
            Toast.makeText(this, "Sei daqui!", Toast.LENGTH_SHORT).show();
            objAndroid.setVisibility(View.INVISIBLE);
        });
    }

    public void volta(View view){
        objAndroid.setVisibility(View.VISIBLE);
    }
}