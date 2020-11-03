package com.example.unidad4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LugaresAdapter extends CursorAdapter {

    public LugaresAdapter(Context context) {
        super(context, null, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombre = (TextView)view.findViewById(R.id.txtNombre);
        nombre.setText(cursor.getString(cursor.getColumnIndex(LugaresProvider.NOMBRE)));

        TextView direccion = (TextView)view.findViewById(R.id.txtDireccion);
        direccion.setText(cursor.getString(cursor.getColumnIndex(LugaresProvider.DIRECCION)));

        TextView telefono = (TextView)view.findViewById(R.id.txtTelefono);
        telefono.setText(cursor.getString(cursor.getColumnIndex(LugaresProvider.TELEFONO)));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.activity_listado_lugares,parent,false);
    }


}
