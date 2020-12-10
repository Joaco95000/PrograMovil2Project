package com.example.menu.Adapter;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.Models.Formulario;
import com.example.menu.R;

import java.util.ArrayList;
public class FormularioAdapter extends RecyclerView.Adapter<FormularioAdapter.ViewHolderDatos> implements View.OnClickListener  {
    ArrayList<Formulario> listaFormularios;
    View view;
    private View.OnClickListener listener;

    public FormularioAdapter(ArrayList<Formulario> listaFormularios) {

        this.listaFormularios = listaFormularios;
    }
    //en este metodo vamos a enlazar nuestro adaptador con el formato_estudiante
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_formularios, null);

        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarFormulario(listaFormularios.get(position));
    }

    @Override
    public int getItemCount() {
        return listaFormularios.size();
    }
    public void setOnclickListener(View.OnClickListener listener){
        this.listener=listener;

    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvNombre,tvMes,tvAnio;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvNombre=itemView.findViewById(R.id.Nombre);
            tvMes=itemView.findViewById(R.id.Mes);
            tvAnio=itemView.findViewById(R.id.Anio);
        }

        public void asignarFormulario(Formulario formulario){
            tvNombre.setText("Nombre: "+formulario.getNombre());
            tvMes.setText("Mes: "+formulario.getMes());
            tvAnio.setText("Anio: "+formulario.getAño());
        }
    }
}
