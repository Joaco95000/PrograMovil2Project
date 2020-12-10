package com.example.menu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AyudanteBaseDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "Proyecto.db",
            NOMBRE_TABLA_FORMULARIO = "Formulario",
            NOMBRE_TABLA_FACTURA = "Factura";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public static String getNombreBaseDeDatos() {
        return NOMBRE_BASE_DE_DATOS;
    }

    public static String getNombreTablaFormulario() {
        return NOMBRE_TABLA_FORMULARIO;
    }

    public static String getNombreTablaFactura() {
        return NOMBRE_TABLA_FACTURA;
    }

    public static int getVersionBaseDeDatos() {
        return VERSION_BASE_DE_DATOS;
    }

    public AyudanteBaseDatos(@Nullable Context context) {
        super(context,NOMBRE_BASE_DE_DATOS,null,VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //aca deberia estar la creacion de tablas o la poblacion de las tablas
        //usaremos el metodo execSQL
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(idForm INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL,mes TEXT NOT NULL,anio INTEGER NOT NULL,estado INTEGER NOT NULL DEFAULT 1);",NOMBRE_TABLA_FORMULARIO));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(idFactura INTEGER PRIMARY KEY AUTOINCREMENT,idForm INTEGER Not NULL,nit INTEGER NOT NULL,numeroFactura INTEGER NOT NULL,fechaEmision TEXT NOT NULL,inporteTotal REAL NOT NULL,codigoControl TEXT NOT NULL,estado INTEGER NOT NULL DEFAULT 1, FOREIGN KEY(IdForm) REFERENCES Formulario(idForm) ); ",NOMBRE_TABLA_FACTURA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
