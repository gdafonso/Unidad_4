package com.example.unidad4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ControlLogin extends LinearLayout {
    private EditText textousuario;
    private EditText textopassword;
    private Button botoncondiciones;
    private Button botonlogin;
    private Button botonacercade;
    private Button botonsalir;
    private TextView labellogin;
    private OnCondicionesListener oncondicioneslistener;
    private OnLoginListener onloginlistener;
    private OnSalirListener onsalirlistener;
    private OnAcercadeListener onacercadelistener;

    public ControlLogin(Context context) {
        super(context);
        inicializar();
    }

    public ControlLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar();
    }

    private void asignarEventos() {
        botoncondiciones.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                oncondicioneslistener.onCondiciones();
            }
        });
        botonlogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onloginlistener.onLogin(textousuario.getText().toString(),
                        textopassword.getText().toString());
            }
        });
        botonacercade.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onacercadelistener.onAcercade();
            }
        });
        botonsalir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onsalirlistener.onSalir();
            }
        });
    }

    private void inicializar() {
        //usamos el layout control_login
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.control_login, this, true);

        //obtenemos referencias
        textousuario = (EditText) findViewById(R.id.editText1);
        textopassword = (EditText) findViewById(R.id.editText2);
        botoncondiciones = (Button) findViewById(R.id.btnAceptar);
        botonlogin = (Button) findViewById(R.id.btnAcceder);
        botonacercade = (Button) findViewById(R.id.button3);
        botonsalir = (Button) findViewById(R.id.button4);
        labellogin = (TextView) findViewById(R.id.textView4);

        //asociamos eventos
        asignarEventos();
    }

    public void setMensaje(String Mensaje) { labellogin.setText(Mensaje); }

    public void setOnLoginListener(OnLoginListener oll) { onloginlistener = oll; }

    public void setOnCondicionesListener(OnCondicionesListener ocl) { oncondicioneslistener = ocl; }

    public void setOnSalirListener(OnSalirListener osl) { onsalirlistener = osl; }

    public void setOnAcercadeListener(OnAcercadeListener oal) { onacercadelistener = oal; }
}
