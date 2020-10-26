package com.example.unidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ControlLogin controldelogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button acceder=(Button)findViewById(R.id.button2);
        acceder.setEnabled(false);

        controldelogin = (ControlLogin) findViewById (R.id.Controllogin1);
        controldelogin.setOnLoginListener (new OnLoginListener() {
            @Override
            public void onLogin (String usuario, String password) {
                //se valida si coinciden los usuarios
                if (usuario.equals("gdafonso") && password.equals("1234")) {
                    controldelogin.setMensaje("Acceso concedido");
                    Intent intencion = new Intent(MainActivity.this, AccesoActivity.class);

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
                Intent intent;

                intent = new Intent(MainActivity.this, CondicionesActivity.class);
                startActivityForResult(intent, 5555);
            }
        });

        controldelogin.setOnSalirListener (new OnSalirListener() {

            public void onSalir () {
                finish();
            }
        });

        controldelogin.setOnAcercadeListener (new OnAcercadeListener() {

            public void onAcercade () {
                Intent intent;

                intent = new Intent(MainActivity.this, AcercadeActivity.class);
                startActivity(intent);
            }
        });
    }
}