package com.example.menu.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.Models.Factura;
import com.example.menu.R;

import java.util.ArrayList;
public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.ViewHolderDatos> {

    ArrayList<Factura> listaFacturas;
    View view;
    LayoutInflater inflater;

    public FacturaAdapter(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
    //en este metodo vamos a enlazar nuestro adaptador con el formato_estudiante
    @NonNull
    @Override
    public FacturaAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_facturas_escaneadas, parent, false);

        return new FacturaAdapter.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacturaAdapter.ViewHolderDatos holder, int position) {
        holder.asignarFactura(listaFacturas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaFacturas.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvNumero,tvFecha,tvImporte, tvCodigo;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.Numero_Factura);
            tvFecha = itemView.findViewById(R.id.Fecha_Emision);
            tvImporte = itemView.findViewById(R.id.Importe);
            tvCodigo = itemView.findViewById(R.id.Codigo);

        }

        public void asignarFactura(Factura factura){
            tvNumero.setText("Numero: "+factura.getNumeroFactura());
            tvFecha.setText("Fecha Emision: "+factura.getFechaEmision());
            tvImporte.setText("Importe: "+factura.getInporteTotal());
            tvCodigo.setText("Codigo" + factura.getCodigoControl());
        }
    }
}
