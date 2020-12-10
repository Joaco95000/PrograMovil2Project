package com.example.menu.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.menu.Controllers.ControllerFormulario;
import com.example.menu.Models.Formulario;
import com.example.menu.R;

import java.io.File;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearForm extends Fragment {
    EditText etNombre,etAño;
    Button btnCrearForm;
    Spinner spMes;
    View view;
    int position;
    ControllerFormulario controllerFormulario;
    Formulario form;
    //variables para el excel
    SQLiteToExcel sqliteToExcel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CrearForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearForm.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearForm newInstance(String param1, String param2) {
        CrearForm fragment = new CrearForm();
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
        view=inflater.inflate(R.layout.fragment_crear_form, container, false);

        etNombre=view.findViewById(R.id.etNombre);
        etAño=view.findViewById(R.id.etAño);
        spMes=(Spinner) view.findViewById(R.id.spMes);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(getActivity(),R.array.opcionesMes,android.R.layout.simple_spinner_item);
        spMes.setAdapter(adapter);
        controllerFormulario=new ControllerFormulario(getActivity());
        form=new Formulario();
        btnCrearForm=view.findViewById(R.id.btnCrearForm);
        btnCrearForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    position=spMes.getSelectedItemPosition();
                    String nombre = etNombre.getText().toString();
                    String mes =spMes.getSelectedItem().toString();
                    String año = etAño.getText().toString();

                    if("".equals(nombre)){
                        etNombre.setError("ingrese el nombre");
                        etNombre.requestFocus();
                        return;
                    }

                    if("".equals(año)){
                        etAño.setError("ingrese el Año");
                        etAño.requestFocus();
                        return;
                    }

                    form.setNombre(nombre);
                    form.setMes(mes);
                    form.setAño(Integer.parseInt(año));

                    long res=controllerFormulario.NuevoFrom(form);
                    if (res>0){
                        Toast.makeText(getActivity(),"Se creo su formulario",Toast.LENGTH_LONG).show();
                        etNombre.setText("");
                        etAño.setText("");
                    }
                    else{
                        Toast.makeText(getActivity(),"no se pudo crear su formulario, intentelo de nuevo",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(getActivity(),""+ex.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });

        return view;
    }
}