package com.example.menu.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.menu.Controllers.ControllerFactura;
import com.example.menu.IComunicaFragments;
import com.example.menu.Models.Factura;
import com.example.menu.Models.Formulario;
import com.example.menu.R;
import com.example.menu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarFactura#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarFactura extends Fragment {
    EditText eNumeroFactura, eFechaEmision, eImporteTotal, eCodigoControl, eNit;
    Button btnAñadir;
    View view;
    Factura factura;
    ControllerFactura controllerFactura;

    //atributos para pasar el formulario a la factura
    Activity activity;
    IComunicaFragments interfaceComunicaFragments;
    int idForm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarFactura() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarFactura.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarFactura newInstance(String param1, String param2) {
        AgregarFactura fragment = new AgregarFactura();
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
        view=inflater.inflate(R.layout.fragment_agregar_factura, container, false);
        controllerFactura=new ControllerFactura(getContext());
        btnAñadir = (Button) view.findViewById(R.id.btnAgregarFactura);
        eNumeroFactura = view.findViewById(R.id.etNumeroFactura);
        eFechaEmision = view.findViewById(R.id.etFechaEmision);
        eImporteTotal = view.findViewById(R.id.etImporteTotal);
        eCodigoControl = view.findViewById(R.id.etCodigo);
        eNit = view.findViewById(R.id.etNit);
        Bundle bundle=getArguments();
        if (bundle!=null){
            idForm=(int)bundle.getSerializable("idForm");
        }
        else {
            Toast.makeText(getContext(),"bundle es nulo: ",Toast.LENGTH_SHORT).show();
        }
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String nit= eNit.getText().toString().trim();
                    String nFactura = eNumeroFactura.getText().toString().trim();
                    String fechaEmision = eFechaEmision.getText().toString().trim();
                    String importeTotal =eImporteTotal.getText().toString().trim();
                    String codigoControl = eCodigoControl.getText().toString().trim();

                    if("".equals(nit)){
                        eNit.setError("ingrese el nit");
                        eNit.requestFocus();
                        return;
                    }
                    if("".equals(nFactura)){
                        eNumeroFactura.setError("ingrese el numero De Factura");
                        eNumeroFactura.requestFocus();
                        return;
                    }
                    if("".equals(fechaEmision)){
                        eFechaEmision.setError("ingrese la fecha de emision");
                        eFechaEmision.requestFocus();
                        return;
                    }
                    if("".equals(importeTotal)){
                        eImporteTotal.setError("ingrese el importe total");
                        eImporteTotal.requestFocus();
                        return;
                    }
                    if("".equals(codigoControl)){
                        eCodigoControl.setError("ingrese el codigo de control");
                        eCodigoControl.requestFocus();
                        return;
                    }
                    factura = new Factura(idForm, Integer.parseInt(nit), Integer.parseInt(nFactura), fechaEmision, Double.parseDouble(importeTotal),codigoControl);
                    long res=controllerFactura.NuevoFormulario(factura);
                    if (res>0)
                    {
                        Toast.makeText(getContext(),"Se agrego la factura con exito",Toast.LENGTH_SHORT).show();
                        eNit.setText("");
                        eNumeroFactura.setText("");
                        eFechaEmision.setText("");
                        eImporteTotal.setText("");
                        eCodigoControl.setText("");
                    }
                    else
                    {
                        Toast.makeText(getContext(),"no se puedo agregar la factura",Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception ex)
                {
                    Toast.makeText(getContext(), "Error al Agregar Factura", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }


}