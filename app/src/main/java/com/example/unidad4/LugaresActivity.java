package com.example.unidad4;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LugaresActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugar);
    }

    public void onClickAddLugar(View view){
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(LugaresProvider.NOMBRE,
                ((EditText)findViewById(R.id.txtNombreLugar)).getText().toString());

        values.put(LugaresProvider.DIRECCION,
                ((EditText)findViewById(R.id.txtDireccionLugar)).getText().toString());

        values.put(LugaresProvider.TELEFONO,
                ((EditText)findViewById(R.id.txtTelefonoLugar)).getText().toString());

        Uri uri = getContentResolver().insert(
                LugaresProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickListarLugares(View view){
        // Retrieve student records
        String URL = "content://com.example.unidad4.LugaresProvider";

        Uri lugares = Uri.parse(URL);
        Cursor c = managedQuery(lugares, null, null, null, "nombre");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(LugaresProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( LugaresProvider.NOMBRE)) +
                                ", " +  c.getString(c.getColumnIndex( LugaresProvider.DIRECCION)) +
                                ", " + c.getString(c.getColumnIndex( LugaresProvider.TELEFONO)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
