package com.example.menu.Models;

import java.io.Serializable;

public class Formulario implements Serializable {
    private int idForm;
    private String nombre;
    private String mes;
    private int año;

    public Formulario(int idForm, String nombre, String mes, int año) {
        this.idForm = idForm;
        this.nombre = nombre;
        this.mes = mes;
        this.año = año;
    }

    private int estado;

    public Formulario(){

    }
    public Formulario(int idForm, String nombre, String mes, int año, int estado) {
        this.idForm = idForm;
        this.nombre = nombre;
        this.mes = mes;
        this.año = año;
        this.estado = estado;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getAño() {
        return año;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }
}
