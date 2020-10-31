package com.example.unidad4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ControlLogin controldelogin;


    private static final int REQUEST_CODE = 1;
    private static final String MESSAGE = "";

    private Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceder=(Button)findViewById(R.id.btnAcceder);
        btnAcceder.setEnabled(false);

        controldelogin = (ControlLogin) findViewById (R.id.Controllogin1);
        controldelogin.setOnLoginListener (new OnLoginListener() {
            @Override
            public void onLogin (String usuario, String password) {
                //se valida si coinciden los usuarios
                if (usuario.equals("gdafonso") && password.equals("1234")) {
                    controldelogin.setMensaje("Acceso concedido");
                    Intent intencion = new Intent(MainActivity.this,
                            AccesoActivity.class);

                    Bundle b = new Bundle();
                    b.putString("Usuario",usuario);
                    b.putString("Password",password);
                    intencion.putExtras(b);
                    startActivity(intencion);
                }
                else
                    controldelogin.setMensaje("No tiene acceso");
            }
        });

        controldelogin.setOnCondicionesListener (new OnCondicionesListener() {

            public void onCondiciones () {
                Intent intent = new Intent(MainActivity.this,
                        CondicionesActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        controldelogin.setOnSalirListener (new OnSalirListener() {

            public void onSalir () {
                finish();
            }
        });

        controldelogin.setOnAcercadeListener (new OnAcercadeListener() {

            public void onAcercade () {
                Intent intent = new Intent(MainActivity.this, AcercadeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                btnAcceder.setEnabled(true);
            } else if(resultCode == Activity.RESULT_CANCELED){
                btnAcceder.setEnabled(false);
            }
        }
    }
}