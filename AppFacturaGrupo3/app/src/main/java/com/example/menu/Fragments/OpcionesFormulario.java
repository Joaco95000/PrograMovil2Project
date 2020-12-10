package com.example.menu.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.menu.Models.Formulario;
import com.example.menu.R;
import com.example.menu.SQLiteToExcel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionesFormulario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionesFormulario extends Fragment {

    View view;
    TextView tvNombre;
    Button btnExportar,btnAgregarFactura,btnbtnListarFacturasEscaneadas,btnScanear,btnListaFacturasManuales;
    String nombre;
    int idForm;

    //variable para el excel
    SQLiteToExcel sqliteToExcel;

    IConnFormFact iConnFormFact;

    public interface IConnFormFact{
        void inputIdForm(int idForm);
        void inputFromScan(int idForm);
        void inputFormScanear(int idForm);
        void inputFormManual(int idForm);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionesFormulario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionesFormulario.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionesFormulario newInstance(String param1, String param2) {
        OpcionesFormulario fragment = new OpcionesFormulario();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof IConnFormFact){
            iConnFormFact = (IConnFormFact) context;
        }else{
            Toast.makeText(context,"debes implementar la Interface: IFragmentoUno",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        iConnFormFact=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_opciones_formulario, container, false);
        tvNombre=view.findViewById(R.id.tvNombreEnviado);
        btnAgregarFactura=view.findViewById(R.id.btnCrearFactura);
        btnExportar=view.findViewById(R.id.btnExportar);
        btnbtnListarFacturasEscaneadas =view.findViewById(R.id.btnListarFacturasEscaneadas);
        btnScanear=view.findViewById(R.id.btnScanear);
        btnListaFacturasManuales=view.findViewById(R.id.btnListarFacturasManales);
        //bundle es lo que esta recibiendo del otro fargment osea el id y el nombre del form

        Bundle bundle=getArguments();
        Formulario form=null;
        if (bundle!=null){
            form=(Formulario)bundle.getSerializable("formulario");
            nombre=form.getNombre();
            tvNombre.setText(nombre);
            idForm=form.getIdForm();

        }
        else {
            Toast.makeText(getContext(),"bundle es nulo: ",Toast.LENGTH_SHORT).show();
        }
        btnListaFacturasManuales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iConnFormFact.inputFormManual(idForm);
            }
        });
        btnScanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iConnFormFact.inputFormScanear(idForm);
            }
        });

        final int finalIdForm = idForm;
        btnAgregarFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iConnFormFact.inputIdForm(finalIdForm);
            }
        });
        btnbtnListarFacturasEscaneadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iConnFormFact.inputFromScan(idForm);
            }
        });

        //Boton para exportar a excel
        final int finalIdForm1 = idForm;
        btnExportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED){
                        String directory_path = getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+"/";
                        File file = new File(directory_path);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        sqliteToExcel = new SQLiteToExcel(getActivity(), "Proyecto.db", directory_path, finalIdForm1);
                        sqliteToExcel.exportSingleTable("Factura", "facturas-"+nombre+".xls", new SQLiteToExcel.ExportListener() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onCompleted(String filePath) {
                                Toast.makeText(getActivity(),"Archivo exportado"+filePath, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(getActivity(),"error: "+e, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    else
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }); //fin del boton para exportar a excel

        return view;
    }

}