package com.example.unidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccesoActivity extends AppCompatActivity {
    private Button btnSalir, btnHospitales, btnLlamada112, btnLlamadaCovid, btnPCRNegativa, btnPCRPositiva, btnLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        btnSalir = findViewById(R.id.btnSalir);
        btnHospitales = findViewById(R.id.btnHospitales);
        btnLlamada112 = findViewById(R.id.btnLlamada112);
        btnLlamadaCovid = findViewById(R.id.btnLlamadaCovid);
        btnPCRNegativa = findViewById(R.id.btnPCRNegativa);
        btnPCRPositiva = findViewById(R.id.btnPCRPositiva);
        btnLugares = findViewById(R.id.btnLugares);

        //localizar controles
        TextView textSaludo = (TextView)findViewById(R.id.textView2);

        //recuperamos la información del intent
        final Bundle b = this.getIntent().getExtras();

        //se construye el mensaje a mostrar
        textSaludo.setText ("Hola, " + b.getString("Usuario"));

        btnPCRPositiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailintent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                mailintent.putExtra(Intent.EXTRA_EMAIL, new String[] { "pcr@covid.es" });
                mailintent.putExtra(Intent.EXTRA_CC, new String[] { "gdafonso@alu.ucam.edu" });
                mailintent.putExtra(Intent.EXTRA_BCC, new String[] { "gdafonso@alu.ucam.edu" });
                mailintent.putExtra(Intent.EXTRA_SUBJECT, "PCR POSITIVA");
                mailintent.putExtra(Intent.EXTRA_TEXT, "Buenos días, este mail es para informar del positivo del usuario: " + b.getString("Usuario"));
                mailintent.setType("text/plain");
                startActivity(mailintent);
            }
        });

        btnPCRNegativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailintent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                mailintent.putExtra(Intent.EXTRA_EMAIL, new String[] { "pcr@covid.es" });
                mailintent.putExtra(Intent.EXTRA_CC, new String[] { "gdafonso@alu.ucam.edu" });
                mailintent.putExtra(Intent.EXTRA_BCC, new String[] { "gdafonso@alu.ucam.edu" });
                mailintent.putExtra(Intent.EXTRA_SUBJECT, "PCR NEGATIVA");
                mailintent.putExtra(Intent.EXTRA_TEXT, "Buenos días, este mail es para informar del negativo del usuario: " + b.getString("Usuario"));
                mailintent.setType("text/plain");
                startActivity(mailintent);
            }
        });

        btnLlamadaCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encodedPhoneNumber = String.format("tel:%s", Uri.encode("900112112"));
                Uri number = Uri.parse(encodedPhoneNumber);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        btnLlamada112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encodedPhoneNumber = String.format("tel:%s", Uri.encode("112"));
                Uri number = Uri.parse(encodedPhoneNumber);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        btnHospitales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:37.9913674,-1.185472?q=hospitals");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoActivity.this, LugaresActivity.class);
                startActivity(intent);
            }
        });


    }
}