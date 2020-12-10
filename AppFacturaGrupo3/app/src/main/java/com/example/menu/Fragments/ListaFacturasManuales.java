package com.example.menu.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.menu.Adapter.FacturaAdapter;
import com.example.menu.AyudanteBaseDatos;
import com.example.menu.Controllers.ControllerFactura;
import com.example.menu.Models.Factura;
import com.example.menu.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFacturasManuales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFacturasManuales extends Fragment {
    View view;
    int idForm;
    ControllerFactura controladorFormulario;
    Factura factura;
    ArrayList<Factura> listaFacturas;
    AyudanteBaseDatos conn;
    RecyclerView reciclerViewManuales;
    FacturaAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaFacturasManuales() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaFacturasManuales.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaFacturasManuales newInstance(String param1, String param2) {
        ListaFacturasManuales fragment = new ListaFacturasManuales();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_lista_facturas_manuales, container, false);
        listaFacturas=new ArrayList<>();


        Bundle bundle=getArguments();
        if (bundle!=null){
            idForm=(int)bundle.getSerializable("idForm");

        }
        else {
            Toast.makeText(getContext(),"bundle es nulo: ",Toast.LENGTH_SHORT).show();
        }
        reciclerViewManuales = view.findViewById(R.id.reciclerViewManuales);
        cargarLista();
        mostrarData();;
        return view;
    }
    private void cargarLista() {
        listaFacturas.clear();
        //Para acceder a la BDD usaremos el ayudante
        AyudanteBaseDatos ayudanteBDD = new AyudanteBaseDatos(getActivity());
        SQLiteDatabase sqLiteDatabase = ayudanteBDD.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT idFactura,numeroFactura,fechaEmision,inporteTotal, codigoControl FROM Factura WHERE idForm="+idForm+" AND estado=1", null);
        if (cursor.moveToNext()) {
            do {
                int idFactura = cursor.getInt(0);
                Log.w("CURSOR", "id: "+cursor.getInt(0));
                int numeroFactura = cursor.getInt(1);
                Log.w("CURSOR", "numeroFactura: "+cursor.getInt(1));
                String fechaEmision = cursor.getString(2);
                Log.w("CURSOR", "FechaEmision: "+cursor.getString(2));
                Double importeTotal = cursor.getDouble(3);
                Log.w("CURSOR", "ImporteTotal: "+cursor.getInt(3));
                String codigoControl = cursor.getString(3);
                Log.w("CURSOR", "Codigo Control: "+cursor.getString(4));
                Factura form = new Factura(idFactura,numeroFactura,fechaEmision,importeTotal, codigoControl);
                listaFacturas.add(form);

            } while (cursor.moveToNext());

            Log.w("lista", listaFacturas.size() + "");
        }
    }
    public void mostrarData(){
        try {
            reciclerViewManuales.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter=new FacturaAdapter(listaFacturas);
            reciclerViewManuales.setAdapter(adapter);

        }
        catch (Exception e){
            throw e;
        }

    }

}