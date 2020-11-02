package com.example.unidad4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class LugaresProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.unidad4.LugaresProvider";
    static final String URL = "content://"+PROVIDER_NAME+"/lugares";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NOMBRE = "nombre";
    static final String DIRECCION = "direccion";
    static final String TELEFONO = "telefono";

    private static HashMap<String, String> LUGARES_PROJECTION_MAP;

    static final int LUGARES = 1;
    static final int LUGAR_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "lugares", LUGARES);
        uriMatcher.addURI(PROVIDER_NAME, "lugares/#", LUGAR_ID);
    }

    /**
     * Definición de la BBDD
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Lugares";
    static final String LUGARES_TABLE_NAME = "sitios";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + LUGARES_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nombre TEXT NOT NULL, " +
                    " direccion TEXT NOT NULL, " +
                    " telefono TEXT NOT NULL);";

    /**
     * Helper para crear y administrar
     * la BBDD
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  LUGARES_TABLE_NAME);
            onCreate(db);
        }
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Crea la BBDD
         */

        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        /**
         * Añade un Lugar
         */
        long rowID = db.insert(	LUGARES_TABLE_NAME, "", contentValues);

        /**
         * Si se ha añadido satisfactoriamente
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Error al añadir el registro en " + uri);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            qb.setTables(LUGARES_TABLE_NAME);

            switch (uriMatcher.match(uri)) {
                case LUGARES:
                    qb.setProjectionMap(LUGARES_PROJECTION_MAP);
                    break;

                case LUGAR_ID:
                    qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                    break;

                default:
            }

            if (sortOrder == null || sortOrder == ""){
                /**
                 * Por defecto se muestran ordenados por nombre
                 */
                sortOrder = NOMBRE;
            }

            Cursor c = qb.query(db,	projection,	selection,
                    selectionArgs,null, null, sortOrder);
            /**
             * register to watch a content URI for changes
             */
            c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case LUGARES:
                count = db.delete(LUGARES_TABLE_NAME, selection, selectionArgs);
                break;

            case LUGAR_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( LUGARES_TABLE_NAME, _ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconocido " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case LUGARES:
                count = db.update(LUGARES_TABLE_NAME, values, selection, selectionArgs);
                break;

            case LUGAR_ID:
                count = db.update(LUGARES_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? "AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconocido " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Muestra todos los lugares
             */
            case LUGARES:
                return "vnd.android.cursor.dir/vnd.example.lugares";
            /**
             * Muestra un Lugar mediante su ID
             */
            case LUGAR_ID:
                return "vnd.android.cursor.item/vnd.example.lugares";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

}
