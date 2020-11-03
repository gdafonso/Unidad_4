package com.example.unidad4;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LugaresFragment extends Fragment {

    /**
     * Textos del layout
     */
    private TextView nombre, direccion, telefono;

    /**
     * Identificador de la actividad
     */
    private long id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_listado_lugares,container,false);

        // Obtención de views
        nombre = (TextView)view.findViewById(R.id.txtNombre);
        direccion = (TextView)view.findViewById(R.id.txtDireccion);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        id = getActivity().getIntent().getLongExtra(LugaresProvider._ID, -1);
        updateView(id);  // Actualzar la vista con los datos de la actividad
    }

    /**
     * Envía todos los datos de la actividad hacia el formulario
     * de actualización
     */
    private void beginUpdate() {
        getActivity()
                .startActivity(
                        new Intent(getActivity(), ListadoLugaresActivity.class)
                                .putExtra(LugaresProvider._ID, id)
                                .putExtra(LugaresProvider.NOMBRE, nombre.getText())
                                .putExtra(LugaresProvider.DIRECCION, direccion.getText())
                                .putExtra(LugaresProvider.TELEFONO, telefono.getText())
                );
    }

    /**
     * Actualiza los textos del layout
     *
     * @param id Identificador de la actividad
     */
    private void updateView(long id) {
        if (id == -1) {
            nombre.setText("");
            direccion.setText("");
            telefono.setText("");

            return;
        }

        Uri uri = ContentUris.withAppendedId(LugaresProvider.CONTENT_URI, id);
        System.out.println(uri.toString());
        Cursor c = getActivity().getContentResolver().query(
                uri,
                null, null, null, null);

        if (!c.moveToFirst())
            return;

        String nombre_text = c.getString(c.getColumnIndex(LugaresProvider.NOMBRE));
        String direccion_text = c.getString(c.getColumnIndex(LugaresProvider.DIRECCION));
        String telefono_text = c.getString(c.getColumnIndex(LugaresProvider.TELEFONO));

        nombre.setText(nombre_text);
        direccion.setText(direccion_text);
        telefono.setText(telefono_text);

        c.close(); // Liberar memoria del cursor
    }
}
