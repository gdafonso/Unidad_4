package com.example.unidad4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HospitalesBD extends SQLiteOpenHelper implements RepositorioHospitales{

    Context contexto;

    public HospitalesBD(Context contexto){
        super(contexto, "hospitales", null, 1);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE hospitales ("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT, " +
                "direccion TEXT, " +
                "longitud REAL, " +
                "latitud REAL, " +
                "telefono INTEGER)");
        sqLiteDatabase.execSQL("INSERT INTO hospitales VALUES (null, "+
                "'Hospital General Universitario Morales Meseguer', "+
                "'Av Marqués de los Vélez, s/n, 30008 Murcia', 37.996629, -1.129394, "+
                "962849300)");
        sqLiteDatabase.execSQL("INSERT INTO hospitales VALUES (null, "+
                "'Hospital Universitario Reina Sofía', "+
                "'Av. Intendente Jorge Palacios, 1, 30003 Murcia', 37.984518, -1.125188, "+
                "968359000)");
        sqLiteDatabase.execSQL("INSERT INTO hospitales VALUES (null, "+
                "'Hospital Clínico Universitario Virgen de la Arrixaca', "+
                "'Ctra. Madrid-Cartagena, s/n, 30120 El Palmar, Murcia', 37.934181, -1.162184, "+
                "968369500)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public Hospital elemento(int id) {
        Hospital hospital = null;
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM lugares WHERE _id = " + id,
                null);
        if (cursor.moveToNext()) {
            hospital = extraeHospital(cursor);
        }
        cursor.close();
        bd.close();
        return hospital;
    }

    @Override
    public void addHospital(Hospital hospital) {

    }

    @Override
    public int nuevo() {
        return 0;
    }

    @Override
    public void borrar(int id) {
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("DELETE FROM hospitales WHERE _id = " + id );
        bd.close();
    }

    @Override
    public int tam() {
        return 0;
    }

    @Override
    public void actualiza(int id, Hospital hospital) {
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("UPDATE hospitales SET nombre = '" + hospital.getNombre() +
                "', direccion = '" + hospital.getDireccion() +
                "', longitud = " + hospital.getPosicion().getLongitud() +
                " , latitud = " + hospital.getPosicion().getLatitud() +
                "', telefono = " + hospital.getTelefono() +
                " WHERE _id = " + id);
        bd.close();
    }

    public static Hospital extraeHospital(Cursor cursor) {
        Hospital hospital = new Hospital();
        hospital.setNombre(cursor.getString(1));
        hospital.setDireccion(cursor.getString(2));
        hospital.setPosicion(new GeoPunto(cursor.getDouble(3), cursor.getDouble(4)));
        hospital.setTelefono(cursor.getInt(7));

        return hospital;
    }
}
