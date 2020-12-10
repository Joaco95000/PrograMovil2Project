package com.example.menu.Models;

import java.io.Serializable;

public class Factura implements Serializable {
    private int idFactura;

    public Factura(int idFactura, int numeroFactura, String fechaEmision, Double inporteTotal, String codigoControl){
        this.idFactura = idFactura;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.inporteTotal = inporteTotal;
        this.codigoControl = codigoControl;
    }

    private int idForm;
    private int nit;
    private int numeroFactura;
    private String fechaEmision;
    private Double inporteTotal;
    private String codigoControl;
    private int estado;

    public Factura(int idForm, int nit, int numeroFactura, String fechaEmision, Double inporteTotal, String codigoControl) {
        this.idForm = idForm;
        this.nit = nit;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.inporteTotal = inporteTotal;
        this.codigoControl = codigoControl;
    }

    public Factura(){

    }
    public Factura(int idFactura, int idForm, int nit, int numeroFactura, String fechaEmision, Double inporteTotal, String codigoControl, int estado) {
        this.idFactura = idFactura;
        this.idForm = idForm;
        this.nit = nit;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.inporteTotal = inporteTotal;
        this.codigoControl = codigoControl;
        this.estado = estado;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getInporteTotal() {
        return inporteTotal;
    }

    public void setInporteTotal(double inporteTotal) {
        this.inporteTotal = inporteTotal;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}