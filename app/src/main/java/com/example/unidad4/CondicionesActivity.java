package com.example.unidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CondicionesActivity extends AppCompatActivity {

    private Button btnAceptado, btnDeclinado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);

        btnAceptado = findViewById(R.id.btnAceptar);
        btnDeclinado = findViewById(R.id.btnAcceder);

        btnAceptado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String result="OK";
                intent.putExtra("condiciones","OK");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnDeclinado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String result="CANCELED";
                intent.putExtra("condiciones","CANCELED");
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }

}