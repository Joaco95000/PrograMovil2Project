package com.example.menu.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.menu.AyudanteBaseDatos;
import com.example.menu.Models.Factura;

public class ControllerFactura {
    private AyudanteBaseDatos ayudanteBaseDatos;

    public ControllerFactura(Context context)
    {
        ayudanteBaseDatos = new AyudanteBaseDatos(context);
    }

    public long NuevoFormulario(Factura factura)
    {
        try{
            SQLiteDatabase sqLiteDatabase = ayudanteBaseDatos.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("idForm",factura.getIdForm());
            contentValues.put("nit",factura.getNit());
            contentValues.put("numeroFactura",factura.getNumeroFactura());
            contentValues.put("fechaEmision",factura.getFechaEmision());
            contentValues.put("inporteTotal",factura.getInporteTotal());
            contentValues.put("codigoControl",factura.getCodigoControl());

            return sqLiteDatabase.insert(ayudanteBaseDatos.getNombreTablaFactura()
                    ,null,contentValues);
        }
        catch (Exception e){
            throw e;
        }

    }
    public long NuevoFormulario(Factura factura,int estado)
    {
        try{
            SQLiteDatabase sqLiteDatabase = ayudanteBaseDatos.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("idForm",factura.getIdForm());
            contentValues.put("nit",factura.getNit());
            contentValues.put("numeroFactura",factura.getNumeroFactura());
            contentValues.put("fechaEmision",factura.getFechaEmision());
            contentValues.put("inporteTotal",factura.getInporteTotal());
            contentValues.put("codigoControl",factura.getCodigoControl());
            contentValues.put("estado",estado);

            return sqLiteDatabase.insert(ayudanteBaseDatos.getNombreTablaFactura()
                    ,null,contentValues);
        }
        catch (Exception e){
            throw e;
        }

    }
}