package com.example.menu.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.menu.Controllers.ControllerFactura;
import com.example.menu.Models.Factura;
import com.example.menu.R;
import com.example.menu.SQLiteToExcel;
import com.google.zxing.Result;


import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.example.menu.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentScaner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentScaner extends Fragment implements ZXingScannerView.ResultHandler  {

    View view;
    Button btnAñadir;
    ZXingScannerView myScannerview;
    int idForm;
    Factura factura;
    ControllerFactura joacoMongol;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentScaner() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentScaner.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentScaner newInstance(String param1, String param2) {
        FragmentScaner fragment = new FragmentScaner();
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
        view=inflater.inflate(R.layout.scaner_fragment, container, false);
        btnAñadir = (Button) view.findViewById(R.id.btnAF);
        joacoMongol=new ControllerFactura(getContext());

        Bundle bundle=getArguments();

        if (bundle!=null){
            idForm=(int)bundle.getSerializable("idForm");
            Toast.makeText(getContext(),""+idForm,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(),"bundle es nulo: ",Toast.LENGTH_SHORT).show();
        }



        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout content = (LinearLayout) view.findViewById(R.id.content);
                myScannerview = new ZXingScannerView(getContext());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED){
                        content.addView(myScannerview);
                        myScannerview.setResultHandler(new ZXingScannerView.ResultHandler() {
                            @Override
                            public void handleResult(Result rawResult) {
                                if(rawResult.getText().equals(""))
                                {
                                    Toast.makeText(getActivity(), "Error Al Escanear", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    String[] parts = rawResult.getText().split("=");
                                    // Toast.makeText(getActivity(), parts[5], Toast.LENGTH_SHORT).show();
                                    String nit = parts[0];
                                    String numeroFactura = parts[1];
                                    String numeroAutorizacion = parts[2];
                                    String fechaEmision = parts[3];
                                    String importeTotal = parts[4];
                                    String codigoControl = parts[5];
                                    String estado = parts[6];

                                    factura=new Factura(idForm,Integer.parseInt(nit),Integer.parseInt(numeroFactura),fechaEmision,Double.parseDouble(importeTotal),codigoControl);
                                    long res=joacoMongol.NuevoFormulario(factura,Integer.parseInt(estado));
                                    if (res>0){
                                        Toast.makeText(getActivity(), "Se registro la factura correctamente", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "el bascope no hizo nada en proyecto de sistemas,por cierto no se ingreso tu factura", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }
                        });
                        myScannerview.startCamera();

                    }

                    else
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
            }
        });
        return  view;
    }

    @Override
    public void handleResult(Result result) {

    }
}