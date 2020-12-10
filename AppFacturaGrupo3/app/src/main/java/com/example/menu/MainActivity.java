package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.menu.Fragments.AgregarFactura;
import com.example.menu.Fragments.CrearForm;
import com.example.menu.Fragments.FragmentQuienesSomos;
import com.example.menu.Fragments.FragmentScaner;
import com.example.menu.Fragments.ListaFacturas;
import com.example.menu.Fragments.ListaFacturasManuales;
import com.example.menu.Fragments.ListaFormularios;
import com.example.menu.Fragments.OpcionesFormulario;
import com.example.menu.Models.Formulario;
import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,IComunicaFragments, OpcionesFormulario.IConnFormFact {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FrameLayout cont;
    //variables para cargar los fragment
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //variable del fragment

    //fragments
    OpcionesFormulario opcionesFormulario;
    ListaFormularios listaFormularios;
    AgregarFactura agregarFactura;
    ListaFacturas listaFacturas;
    FragmentScaner fragmentScaner;
    ListaFacturasManuales listaFacturasManuales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigationView);

        //eestabalecemos el evento click al navigation
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        cont=findViewById(R.id.container);
        opcionesFormulario =new OpcionesFormulario();
        agregarFactura=new AgregarFactura();
        listaFacturas=new ListaFacturas();
        fragmentScaner=new FragmentScaner();
        listaFormularios=new ListaFormularios();
        listaFacturasManuales=new ListaFacturasManuales();

        //cargar fragment principal
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,listaFormularios);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        try {
            if (item.getItemId()==R.id.crearFormulario){
                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new CrearForm());
                fragmentTransaction.commit();
            }
            if (item.getItemId()==R.id.listaFormulario){
                try {
                    fragmentManager=getSupportFragmentManager();
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container,listaFormularios);
                    fragmentTransaction.commit();
                }
                catch (Exception e){
                    Toast.makeText(this,"Error "+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
            if (item.getItemId()==R.id.quienesSomos){
                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new FragmentQuienesSomos());
                fragmentTransaction.commit();
            }
        }
        catch (Exception ex){
            Toast.makeText(this,"Error "+ex,Toast.LENGTH_LONG).show();
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("¿Salir?");
                dialogo1.setMessage("¿ Esta seguro de querer cerrar la apliccación?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        aceptar();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar();
                    }
                });
                dialogo1.show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void aceptar() {
        finish();
    }

    public void cancelar() {

    }

    @Override
    public void enviarForm(Formulario form) {
        opcionesFormulario= new OpcionesFormulario();

        Bundle bundle= new Bundle();
        bundle.putSerializable("formulario",form);
        //objeto budle para transportar la info

        opcionesFormulario.setArguments(bundle);
        //abrir fragment

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,opcionesFormulario);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void inputIdForm(int idForm) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("idForm",idForm);
        //objeto budle para transportar la info

        agregarFactura.setArguments(bundle);
        //abrir fragment

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,agregarFactura);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void inputFromScan(int idForm) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("idForm",idForm);
        //objeto budle para transportar la info

        listaFacturas.setArguments(bundle);
        //abrir fragment

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,listaFacturas);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void inputFormScanear(int idForm) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("idForm",idForm);
        //objeto budle para transportar la info

        fragmentScaner.setArguments(bundle);
        //abrir fragment
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragmentScaner);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void inputFormManual(int idForm) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("idForm",idForm);
        //objeto budle para transportar la info

        listaFacturasManuales.setArguments(bundle);
        //abrir fragment
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,listaFacturasManuales);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}