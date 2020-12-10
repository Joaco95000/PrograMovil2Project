package com.example.menu.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.menu.AyudanteBaseDatos;
import com.example.menu.Models.Formulario;

public class ControllerFormulario {
    private AyudanteBaseDatos ayudanteBaseDatos;

    public ControllerFormulario(Context context) {
        ayudanteBaseDatos = new AyudanteBaseDatos(context);
    }
    public long NuevoFrom(Formulario form){
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDatos.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",form.getNombre());
        contentValues.put("mes",form.getMes());
        contentValues.put("anio",form.getAño());
        return sqLiteDatabase.insert(ayudanteBaseDatos.getNombreTablaFormulario()
                ,null,contentValues);
    }
}
