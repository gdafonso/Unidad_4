package com.example.unidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AccesoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        //localizar controles
        TextView textSaludo = (TextView)findViewById(R.id.textview2);

        //recuperamos la información del intent
        Bundle b = this.getIntent().getExtras();

        //se construye el mensaje a mostrar
        textSaludo.setText("Hola, " + b.getString("Usuario"));
    }
}