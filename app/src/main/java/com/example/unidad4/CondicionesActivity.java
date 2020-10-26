package com.example.unidad4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CondicionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);

        //Intent intencion=new Intent();
        //intencion.putExtra("condiciones","OK");
        //setResult(Activity.RESULT_OK,intencion);
        //finish();

        @Override
        protected void onActivityResult (int requestCode, int resultCode) {
            final Button acceder=(Button) findViewById (R.id.button2);
            if (requestCode==5555 && resultCode==RESULT_OK){
                String resultado = data.getExtras().getString("condiciones");
                if (resultado.equals("OK"))
                    acceder.setEnabled(true);
                else
                    acceder.setEnabled(false);
            }
        }
    }

}