package com.example.menu.Fragments;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.Adapter.FormularioAdapter;
import com.example.menu.Controllers.ControllerFormulario;

import com.example.menu.IComunicaFragments;
import com.example.menu.Models.Formulario;
import com.example.menu.R;

import java.util.ArrayList;
import com.example.menu.AyudanteBaseDatos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFormularios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFormularios extends Fragment {
    FormularioAdapter adapter;
    RecyclerView rvListaFormularios;

    ControllerFormulario controladorFormulario;
    Formulario factura;
    ArrayList<Formulario> listaInfo=new ArrayList<>();
    AyudanteBaseDatos conn;
    View view;

    //referencias para comunicar fragments
    Activity activity;
    IComunicaFragments interfaceComunicaFragments;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaFormularios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaFormularios.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaFormularios newInstance(String param1, String param2) {
        ListaFormularios fragment = new ListaFormularios();
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
        view=inflater.inflate(R.layout.fragment_lista_formularios, container, false);

        rvListaFormularios = view.findViewById(R.id.reciclerView);
        cargarLista();
        mostrarData();
        return view;
    }
    private void cargarLista() {
        listaInfo.clear();
        //Para acceder a la BDD usaremos el ayudante
        AyudanteBaseDatos ayudanteBDD = new AyudanteBaseDatos(getActivity());
        SQLiteDatabase sqLiteDatabase = ayudanteBDD.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT idForm,nombre,mes,anio FROM Formulario", null);
        if (cursor.moveToNext()) {
            do {
                int idForm = cursor.getInt(0);
                Log.w("CURSOR", "id: "+cursor.getInt(0));
                String nombre = cursor.getString(1);
                Log.w("CURSOR", "nombre: "+cursor.getString(1));
                String mes = cursor.getString(2);
                Log.w("CURSOR", "mes: "+cursor.getString(2));
                int anio = cursor.getInt(3);
                Log.w("CURSOR", "año: "+cursor.getInt(3));
                Formulario form = new Formulario(idForm,nombre,mes,anio);
                listaInfo.add(form);

            } while (cursor.moveToNext());

            Log.w("lista", listaInfo.size() + "");
        }
    }
    public void mostrarData(){
        try {
            rvListaFormularios.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter=new FormularioAdapter(listaInfo);
            rvListaFormularios.setAdapter(adapter);

            adapter.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nombre=listaInfo.get(rvListaFormularios.getChildAdapterPosition(v)).getIdForm();
                    interfaceComunicaFragments.enviarForm(listaInfo.get(rvListaFormularios.getChildAdapterPosition(v)));
                }
            });
        }
        catch (Exception e){
           throw e;
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.activity=(Activity)context;
            interfaceComunicaFragments =(IComunicaFragments)this.activity;
        }
        else
        {
            Toast.makeText(context,"Implemnta la interfaz",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}